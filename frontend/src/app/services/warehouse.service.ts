import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';
import { Warehouse } from '../models/warehouse';

@Injectable({
  providedIn: 'root'
})
export class WarehouseService {

  currentTotal: number = 0;

  constructor() { }

  warehouse: Warehouse = new Warehouse ('', '', 0)

  warehouses: Warehouse[] = [
    new Warehouse('GPU Warehouse', 'Taiwan', 0),
    new Warehouse('CPU Warehouse', 'China', 0),
    new Warehouse('PSU Warehouse', 'Thailand', 0)
  ]

  createWarehouse(newWarehouse: Warehouse) {
    console.log('inside WarehouseService');
    this.warehouse = newWarehouse;
    console.log(this.warehouse.warehouseName);
  }

  
  

}
