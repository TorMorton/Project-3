package com.proj3.warehouses.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.proj3.warehouses.constants.HttpConstants;
import com.proj3.warehouses.jwt.JwtFilter;
import com.proj3.warehouses.models.Bill;
import com.proj3.warehouses.repositories.BillRepository;
import com.proj3.warehouses.utils.WarehouseUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

import java.util.*;
import java.util.List;
import java.util.stream.Stream;

import static com.proj3.warehouses.utils.WarehouseUtils.getFileByteArray;


@Slf4j
@Service
public class BillServiceImpl implements BillService {

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    BillRepository billRepository;

    @Override
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        log.info("Inside generateReport");
        try {
            String fileName;  // used to generate unique name for DB
            if (validateRequestMap(requestMap)) {
               if (requestMap.containsKey("isGenerate") && !(Boolean)requestMap.get("isGenerate")) {
                   fileName = (String) requestMap.get("uuid");
               } else {
                      fileName = WarehouseUtils.getUUID();
                      requestMap.put("uuid", fileName);
                      insertBill(requestMap);
               }

               String data = "Name: " + requestMap.get("name") + "\n"
                       + "Contact Number: " + requestMap.get("contactNumber") + "\n"
                       + "Email: " + requestMap.get("email") + "\n"
                       + "Payment Method: " + requestMap.get("paymentMethod");

               Document document = new Document();
               PdfWriter.getInstance(document, new FileOutputStream(HttpConstants.STORE_LOCATION + "\\" + fileName + ".pdf"));

               document.open();
               setBorderInPdf(document);

               Paragraph paragraphHeader = new Paragraph("GameRigs Computing LLC", getFont("Header"));
               paragraphHeader.setAlignment(Element.ALIGN_CENTER);
               document.add(paragraphHeader);

               Paragraph paragraphText = new Paragraph(data + "\n \n", getFont("Data"));
               document.add(paragraphText);

               PdfPTable table = new PdfPTable(5);  // creates 5 columns in the PDF
               table.setWidthPercentage(100);
               addTableHeader(table);

               JSONArray jsonArray = WarehouseUtils.getJsonArrayFromString((String) requestMap.get("productDetails"));
               for (int j = 0; j < jsonArray.length(); j++) {
                     addRows(table, WarehouseUtils.getMapFromJson(jsonArray.getString(j)));
               }
               document.add(table);

               // add footer
               Paragraph paragraphFooter = new Paragraph("Total: " + requestMap.get("totalAmount") + "\n" + "Thank you for your order!", getFont("Data"));
               document.add(paragraphFooter);
               document.close();
               return new ResponseEntity<>("{\"uuid\":\"" + fileName + "\"}", HttpStatus.OK);  // key is uuid and value is fileName
            }
            return WarehouseUtils.getResponseEntity("Invalid Request. Required Data Not Found.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WarehouseUtils.getResponseEntity("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void addRows(PdfPTable table, Map<String, Object> data) {
        log.info("Inside addRow");
        table.addCell((String) data.get("name"));
        table.addCell((String) data.get("category"));
        table.addCell((String) data.get("quantity"));
        table.addCell(Double.toString((Double)data.get("price")));
        table.addCell(Double.toString((Double)data.get("total")));
        log.info("Exiting addRow");
    }

    private void addTableHeader(PdfPTable table) {
        log.info("Inside addTableHeader");
        Stream.of("Name", "Category", "Quantity", "Price", "Sub Total")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);  // fills in the header with gray
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setVerticalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
        log.info("Exiting addTableHeader");
    }

    private Font getFont(String type) {
        log.info("Inside getFont");
        switch (type) {
            case "Header":
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18, BaseColor.BLACK);
                headerFont.setStyle(Font.BOLD);
                return headerFont;
            case "Data":
                Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.BLACK);
                dataFont.setStyle(Font.BOLD);
                return dataFont;
            default:
                return new Font();
        }
    }


    private void setBorderInPdf(Document document) throws DocumentException {
        log.info("Inside setBorderInPdf");
        Rectangle rectangle = new Rectangle(577, 825, 18, 15);
        rectangle.enableBorderSide(1);
        rectangle.enableBorderSide(2);
        rectangle.enableBorderSide(4);
        rectangle.enableBorderSide(8);
        rectangle.setBorderColor(BaseColor.BLACK);
        rectangle.setBorderWidth(1);
        document.add(rectangle);
    }

    private void insertBill(Map<String, Object> requestMap) {
        try {
            Bill bill = new Bill();
            bill.setUuid((String) requestMap.get("uuid"));
            bill.setName((String) requestMap.get("name"));
            bill.setEmail((String) requestMap.get("email"));
            bill.setContactNumber((String) requestMap.get("contactNumber"));
            bill.setPaymentMethod((String) requestMap.get("paymentMethod"));
            bill.setTotal(Integer.parseInt((String) requestMap.get("totalAmount")));
            bill.setProductDetails((String) requestMap.get("productDetails"));
            bill.setCreatedBy(jwtFilter.getCurrentUser());
            billRepository.save(bill);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validateRequestMap(Map<String, Object> requestMap) {
        return requestMap.containsKey("name")
                && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email")
                && requestMap.containsKey("paymentMethod")
                && requestMap.containsKey("productDetails")
                && requestMap.containsKey("totalAmount");
    }

    @Override
    public ResponseEntity<List<Bill>> getBills() {
        List<Bill> list = new ArrayList<>();
        if (jwtFilter.isAdmin()) {
            list = billRepository.getAllBills();
        } else {
            list = billRepository.getBillsByUserName(jwtFilter.getCurrentUser());
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap) {
        log.info("Inside getPdf: requestMap {}", requestMap);
        try {
            byte[] byteArray = new byte[0];
            if (!requestMap.containsKey("uuid") && (validateRequestMap(requestMap))) {
                return new ResponseEntity<>(byteArray, HttpStatus.BAD_REQUEST);
            }
            String filePath = HttpConstants.STORE_LOCATION + "\\" + (String) requestMap.get("uuid") + ".pdf";
            if (WarehouseUtils.isFilePresent(filePath)) {
                byteArray = getFileByteArray(filePath);
                return new ResponseEntity<>(byteArray, HttpStatus.OK);
            } else {
                requestMap.put("isGenerate", false);
                generateReport(requestMap);
                byteArray = getFileByteArray(filePath);
                return new ResponseEntity<>(byteArray, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> deleteBill(Integer id) {
        try {
            Optional optional = billRepository.findById(id);
            if (optional.isPresent()) {
                billRepository.deleteById(id);
                return WarehouseUtils.getResponseEntity("Bill Deleted Successfully", HttpStatus.OK);
            }
            return WarehouseUtils.getResponseEntity("Bill Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WarehouseUtils.getResponseEntity("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
