import { Component, OnInit } from '@angular/core';
import { Warehouse } from '../models/warehouse';
import { CRUDService } from '../services/crud.service';
import { WarehouseService } from '../services/warehouse.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  gpuWarehouseURL: string = 'gpu_inventory/'
  cpuWarehouseURL: string = 'cpu_inventory/'
  psuWarehouseURL: string = 'psu_inventory/'

  warehouses: Warehouse[] = this.warehouseService.warehouses;

  constructor(private crudService: CRUDService, private warehouseService: WarehouseService) {}
  

  // Functions

  ngOnInit(): void {
    this.getCurrentTotal();
  }

  getWarehouse(warehouseURL: string) {
    sessionStorage.setItem("warehouseURL", warehouseURL);
    console.log("session storage");
    console.log(sessionStorage.getItem("warehouseURL"));
    this.crudService.getAll();
  }

  getCurrentTotal(): void {
    let counter: number = 0;
    this.crudService.getCurrentTotal().subscribe(data => {
      console.log(data);
      for (let item of data.body) {
          this.warehouseService.warehouses[counter].currentTotal = item.currentTotal;
          counter++;
      }
    })
  }
}
