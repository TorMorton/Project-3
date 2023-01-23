import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class CRUDService {

  apiUrl: string = environment.apiURL;
  warehouseURL: string = "";
  tempUrl: string = "";

  constructor(private httpClient: HttpClient) { }

  // functions to get all
  getAll(): Observable<HttpResponse<any>> {
    this.tempUrl = this.apiUrl + localStorage.getItem(this.warehouseURL);
    console.log(this.tempUrl);
    return this.httpClient.get<any>(this.tempUrl, {observe: 'response'})
  }

  save(object: Product): Observable<HttpResponse<any>> {
    this.tempUrl = this.apiUrl + localStorage.getItem(this.warehouseURL);
    console.log('inside crud save')
    console.log(this.tempUrl);
    console.log( object);
    return this.httpClient.post<any>(this.tempUrl, object, {observe: 'response'});
  }

  update(object: Product, id: number): Observable<HttpResponse<any>> {
    this.tempUrl = this.apiUrl + localStorage.getItem(this.warehouseURL);
    console.log('inside crud update')
    console.log(this.tempUrl);
    console.log(object);
    return this.httpClient.put<any>(this.tempUrl + id, object, {observe: 'response'});
  }

  deleteById(id: number): Observable<HttpResponse<any>> {
    this.tempUrl = this.apiUrl + localStorage.getItem(this.warehouseURL);
    console.log('inside crud deleteById')
    console.log(this.tempUrl);
    console.log(id);
    return this.httpClient.delete<any>(this.tempUrl + id, {observe: 'response'});  
  }

  deleteAll(): Observable<HttpResponse<any>> {
    this.tempUrl = this.apiUrl + localStorage.getItem(this.warehouseURL);
    console.log("inside deleteAll")
    console.log(this.tempUrl);
    return this.httpClient.delete<any>(this.tempUrl, {observe: 'response'});  
  }

}
