import { Component } from '@angular/core';
import { CRUDService } from '../services/crud.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

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
