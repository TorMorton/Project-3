import { Component } from '@angular/core';
import { NgbDropdownModule, NgbNavModule } from '@ng-bootstrap/ng-bootstrap';
import { CRUDService } from '../services/crud.service';
import { WarehousesComponent } from '../warehouses/warehouses.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  gpuWarehouseURL: string = 'gpu_inventory/'
  cpuWarehouseURL: string = 'cpu_inventory/'

  

  constructor(private crudService: CRUDService) {}

  getWarehouse(warehouseURL: string) {
    localStorage.setItem(this.crudService.warehouseURL, warehouseURL);
    console.log("local storage")
    console.log(localStorage.getItem(this.crudService.warehouseURL))
    this.crudService.warehouseURL = warehouseURL;
    this.crudService.getAll;
  }

}