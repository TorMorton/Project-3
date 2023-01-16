import { Injectable } from '@angular/core';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor() { }

  product: Product = new Product('', '', 0);

  createProduct(newProduct: Product) {
    console.log('inside ProductService');
    this.product = newProduct;
    console.log(this.product.cost);
  }
}
