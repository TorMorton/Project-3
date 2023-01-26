import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators} from '@angular/forms';
import { Product } from '../models/product';
import { CRUDService } from '../services/crud.service';
import { ProductService } from '../services/product.service';
import { Observable } from 'rxjs';
import { WarehouseService } from '../services/warehouse.service';
import { Warehouse } from '../models/warehouse';
import { ThisReceiver } from '@angular/compiler';

@Component({
  selector: 'app-warehouses',
  templateUrl: './warehouses.component.html',
  styleUrls: ['./warehouses.component.css']
})
export class WarehousesComponent implements OnInit{

  // class properties
  isCollapsed: boolean = true;
  inventory: any = [];
  tempInventory: any;
  totalInStock: number = 0;
  limit = 100 - this.totalInStock;
  warehouses: Warehouse[] = this.warehouseService.warehouses;
  nameId: number = 0;


  // constructor

  constructor(private crudService: CRUDService, private productService: ProductService, private warehouseService: WarehouseService, private fb: FormBuilder,) {
  //   this.crudService.getAll().subscribe(data => {
  //     console.log(data);
  //     this.inventory = data.body
  // })
  }


  // form properties
  productForm = this.fb.group(
    {
      manufacturer: ['', Validators.compose([Validators.maxLength(50), Validators.required])],
      model: ['', Validators.compose([Validators.maxLength(50), Validators.required])],
      price: [0, Validators.compose([Validators.required])],
      numInStock: [1, Validators.compose([Validators.required, Validators.max(this.limit)])]
    }
  );

  get manufacturer() {
    return this.productForm.get('manufacturer');
  }

  get model() {
    return this.productForm.get('model');
  }

  get price() {
    return this.productForm.get('price');
  }

  get numInStock() {
    return this.productForm.get('numInStock');
  }

  tempProduct: any = {
    manufacturer: 'manufacturer',
    model: 'model',
    cost: 0,
    numInStock: 0
  };

  tempWarehouse: any = {
    name: this.warehouseService.warehouses[this.nameId].warehouseName,
    location: this.warehouseService.warehouses[this.nameId].location,
    capacity: 100,
    currentTotal: 0
  };

  tempProductId: number = 0;


  // methods
  ngOnInit(): void {
    this.displayAll();

    switch (sessionStorage.getItem("warehouseURL")) {
      case ("gpu_inventory/") :
        this.nameId = 0;
        break;
      case ("cpu_inventory/") :
        this.nameId = 1;
        break;
      case ("psu_inventory/") :
        this.nameId = 2;
        break;
    }    
  }

  displayAll() {
    this.crudService.getAll().subscribe(data => {
      console.log(data);
      this.inventory = data.body;
      console.log(this.inventory);
      this.countCurrentTotal();
  })
    this.tempInventory = null;
  }

  displayById(id: number) {
    for (let item of this.inventory) { 
      if (item.id == id) {
        this.tempInventory = [];
        this.tempInventory.push(item);
        break;        
      }
    }
  }

  findById(id: number) {
    for (let item of this.inventory) { 
      if (item.id == id) {
        this.tempProduct.manufacturer = item.manufacturer;
        this.tempProduct.model = item.model;
        this.tempProduct.cost = item.cost;
        this.tempProduct.id = id;
        this.tempProduct.numInStock = item.numInStock;
        break;        
      }
    }
  }

  onSubmit(): void {
    this.productService.createProduct(new Product(this.manufacturer?.value!, 
                                                  this.model?.value!, 
                                                  this.price?.value!,
                                                  this.numInStock?.value!));
    this.tempProduct.manufacturer = this.productService.product.manufacturer;
    this.tempProduct.model = this.productService.product.model;
    this.tempProduct.cost = this.productService.product.cost;
    this.tempProduct.numInStock = this.productService.product.numInStock;
  }

  save() {
    this.countCurrentTotal();
    this.crudService.save(this.tempProduct).subscribe(data => {
      console.log(data);
      this.displayAll();
      
    });
  }
  
  update() {
    console.log('inside warehouse update()')
    if(this.tempProduct.numInStock + this.totalInStock <= this.warehouseService.warehouses[this.nameId].capacity ){
      this.crudService.update(this.tempProduct, this.tempProduct.id).subscribe(data => {
        console.log(data);
        this.displayAll();
      });
    }
  }

  deleteById() {
      console.log('inside warehouse deleteById()')
      this.crudService.deleteById(this.tempProduct.id).subscribe(async data => {
      console.log(data);
      this.displayAll();
    });
  }

  deleteAll() {
    for (let item of this.inventory) {
      this.crudService.deleteById(item.id).subscribe(data => {
        console.log(data);
      });
    }
    this.displayAll();
  }

  countCurrentTotal(): void {
    console.log('run currentTotal')
    this.totalInStock = 0;
    for (let item of this.inventory) {
      this.totalInStock += item.numInStock;
    }
    this.tempWarehouse.currentTotal = this.totalInStock;
    this.crudService.updateCurrentTotal(this.tempWarehouse, this.nameId + 1).subscribe(data => {
      console.log(data)
    })
  }
}
