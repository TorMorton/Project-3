import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from '../models/product';
import { Warehouse } from '../models/warehouse';

@Injectable({
  providedIn: 'root'
})
export class CRUDService {

  apiUrl: string = environment.apiURL;
  tempUrl: string = "";

  constructor(private httpClient: HttpClient) { }

  // functions to get all
  getAll(): Observable<HttpResponse<any>> {
    this.tempUrl = this.apiUrl + sessionStorage.getItem("warehouseURL");
    console.log("crud tempURL");
    console.log(this.tempUrl);
    return this.httpClient.get<any>(this.tempUrl, {observe: 'response'})
  }

  getCurrentTotal(): Observable<HttpResponse<any>> {
    this.tempUrl = this.apiUrl + 'warehouses/';
    console.log("crud tempURL");
    console.log(this.tempUrl);
    return this.httpClient.get<any>(this.tempUrl, {observe: 'response'})
  }

  save(object: Product): Observable<HttpResponse<any>> {
    this.tempUrl = this.apiUrl + sessionStorage.getItem("warehouseURL");
    console.log('inside crud save')
    console.log(this.tempUrl);
    console.log( object);
    return this.httpClient.post<any>(this.tempUrl, object, {observe: 'response'});
  }

  update(object: Product, id: number): Observable<HttpResponse<any>> {
    this.tempUrl = this.apiUrl + sessionStorage.getItem("warehouseURL");
    console.log('inside crud update')
    console.log(this.tempUrl);
    console.log(object);
    return this.httpClient.put<any>(this.tempUrl + id, object, {observe: 'response'});
  }

  updateCurrentTotal(object: Warehouse, id: number): Observable<HttpResponse<any>> {
    this.tempUrl = this.apiUrl + 'warehouses/' + id;
    console.log('inside crud update')
    console.log(this.tempUrl);
    console.log(object);
    return this.httpClient.put<any>(this.tempUrl, object, {observe: 'response'});
  }

  deleteById(id: number): Observable<HttpResponse<any>> {
    this.tempUrl = this.apiUrl + sessionStorage.getItem("warehouseURL");
    console.log('inside crud deleteById')
    console.log(this.tempUrl);
    console.log(id);
    return this.httpClient.delete<any>(this.tempUrl + id, {observe: 'response'});  
  }

  deleteAll(): Observable<HttpResponse<any>> {
    this.tempUrl = this.apiUrl + sessionStorage.getItem("warehouseURL");
    console.log("inside deleteAll")
    console.log(this.tempUrl);
    return this.httpClient.delete<any>(this.tempUrl, {observe: 'response'});  
  }

}
