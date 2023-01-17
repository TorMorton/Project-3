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

  constructor(private crudService: CRUDService) {}

  getWarehouse(warehouseURL: string) {
    this.crudService.warehouseURL = warehouseURL;
    this.crudService.getAll;
  }

  

}