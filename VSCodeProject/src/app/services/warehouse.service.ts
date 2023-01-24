import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';
import { Warehouse } from '../models/warehouse';

@Injectable({
  providedIn: 'root'
})
export class WarehouseService {

  constructor() { }

  warehouse: Warehouse = new Warehouse ('', '', 0)
  warehouseName: string = '';

  createWarehouse(newWarehouse: Warehouse) {
    console.log('inside WarehouseService');
    this.warehouse = newWarehouse;
    console.log(this.warehouse.warehouseName);
  }

  private currentWarehouse = new BehaviorSubject(this.warehouseName);

  myWarehouse = this.currentWarehouse.asObservable();

}
