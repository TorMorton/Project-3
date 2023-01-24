import { Component } from '@angular/core';
import { NgbDropdownModule, NgbNavModule } from '@ng-bootstrap/ng-bootstrap';
import { CRUDService } from '../services/crud.service';
import { WarehouseService } from '../services/warehouse.service';
import { WarehousesComponent } from '../warehouses/warehouses.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  gpuWarehouseURL: string = 'gpu_inventory/'
  cpuWarehouseURL: string = 'cpu_inventory/'
  psuWarehouseURL: string = 'psu_inventory/'
  
  gpuWarehouse: string = 'GPU Warehouse'
  cpuWarehouse: string = 'CPU Warehouse'
  psuWarehouse: string = 'PSU Warehouse'

  constructor(private crudService: CRUDService, private warehouseService: WarehouseService) {}

  getWarehouse(warehouseURL: string, warehouseName: string) {
    sessionStorage.setItem("warehouseURL", warehouseURL);
    console.log("session storage");
    console.log(sessionStorage.getItem("warehouseURL"));
    this.crudService.getAll();
  }

  refresh() {
    setTimeout(() => {
      window.location.reload();
    }, 25);
  }

  

}