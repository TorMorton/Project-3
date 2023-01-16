import { Component } from '@angular/core';
import { FormBuilder, Validators} from '@angular/forms';
import { Product } from '../models/product';
import { CRUDService } from '../services/crud.service';
import { ProductService } from '../services/product.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-warehouses',
  templateUrl: './warehouses.component.html',
  styleUrls: ['./warehouses.component.css']
})
export class WarehousesComponent{

  // class properties
  isCollapsed: boolean = true;
  inventory: any = [];
  tempInventory: any;

  // constructor

  constructor(private crudService: CRUDService, private productService: ProductService, private fb: FormBuilder,) {
    this.crudService.getAll().subscribe(data => {
      console.log(data);
      this.inventory = data.body
  })
  }


  // form properties
  productForm = this.fb.group(
    {
      manufacturer: ['', Validators.compose([Validators.maxLength(50), Validators.required])],
      model: ['', Validators.compose([Validators.maxLength(50), Validators.required])],
      price: [0, Validators.compose([Validators.required])],
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

  tempProduct: any = {
    manufacturer: 'manufacturer',
    model: 'model',
    cost: 0 
  };

  tempProductId: number = 0;


  // methods
  displayAll() {
    this.crudService.getAll().subscribe(data => {
      console.log(data);
      this.inventory = data.body;
  })
    this.tempInventory = null;
    console.log('displaAll() ran')
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
        break;        
      }
    }
  }

  onSubmit(): void {
    this.productService.createProduct(new Product(this.manufacturer?.value!, 
                                                  this.model?.value!, 
                                                  this.price?.value!));
    this.tempProduct.manufacturer = this.productService.product.manufacturer;
    this.tempProduct.model = this.productService.product.model;
    this.tempProduct.cost = this.productService.product.cost;
  }

  save() {
    this.crudService.save(this.tempProduct).subscribe(data => {
      console.log(data);
      this.displayAll();
    });
  }
  
  update() {
    console.log('inside warehouse update()')
    this.crudService.update(this.tempProduct, this.tempProduct.id).subscribe(data => {
      console.log(data);
      this.displayAll();
    });
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
  
}
