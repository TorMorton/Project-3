import { Injectable } from '@angular/core';
import { Warehouse } from '../models/warehouse';

@Injectable({
  providedIn: 'root'
})
export class WarehouseService {

  constructor() { }

  warehouse: Warehouse = new Warehouse ('', '', 0)

  createWarehouse(newWarehouse: Warehouse) {
    console.log('inside WarehouseService');
    this.warehouse = newWarehouse;
    console.log(this.warehouse.warehouseName);
  }

  

}
