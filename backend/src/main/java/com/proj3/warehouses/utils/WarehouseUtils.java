package com.proj3.warehouses.utils;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WarehouseUtils {

    private WarehouseUtils() {

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<String>("{\"message\":\"" + responseMessage + "\"}", httpStatus);
    }

    public static String getUUID() {
        Date date = new Date();
        long time = date.getTime();
        return "GameRigsInvoice-" + time;  // gives us a unique time
    }

    // method for converting JSON/parses the JSON
    public static JSONArray getJsonArrayFromString(String data) throws JSONException {
        log.info("Inside getJsonArrayFromString");
        JSONArray jsonArray = new JSONArray(data);
        log.info("exiting getJsonArrayFromString");
        return jsonArray;
    }

    // formatting JSON
    public static Map<String, Object> getMapFromJson(String data) throws JSONException {
        if (!Strings.isNullOrEmpty(data))
            return new Gson().fromJson(data, new TypeToken<Map<String, Object>>() {
            }.getType());
        return new HashMap<>();
    }

    public static Boolean isFilePresent(String filePath) {
        log.info("Inside isFilePresent {}", filePath);
        try {
            File file = new File(filePath);
            return (filePath != null && file.exists() ? Boolean.TRUE : Boolean.FALSE);

        } catch (Exception e) {
            log.error("Exception in isFilePresent: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    public static byte[] getFileByteArray(String filePath) throws Exception {
        File initialFile = new File(filePath);
        InputStream targetStream = new FileInputStream(initialFile);
        byte[] byteArray = IOUtils.toByteArray(targetStream);
        targetStream.close();
        return byteArray;
    }
}
