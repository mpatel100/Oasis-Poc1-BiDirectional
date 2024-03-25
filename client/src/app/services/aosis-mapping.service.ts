import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'envrionments/envrionment';
import { catchError, map, of, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AosisMappingService {

  private baseUrl = location.host?.includes('localhost') ? '' : `${environment.baseUrl}`; 
  constructor(private httpClient: HttpClient) { }

  getFormData() {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpClient.get(`${this.baseUrl}/oasis/poc2/getAllPoc2Entities`,{ headers, responseType: 'json'})
     .pipe(
      map((data: any) => {
        return data;
      }),
      catchError((error: HttpErrorResponse) => {
        return throwError(() => error);
      }));
  }

  submitFormData(formData: any) {
    let headers = new HttpHeaders();
    headers.set('Content-Type', 'application/json');
    headers.set('accept', '*/*');

    return this.httpClient.put<any>(`${this.baseUrl}/oasis/poc2/updateDynamicEntity`, formData, { headers: headers })
     .pipe(
      map((data: any) => { 
        return data;
      }),
      catchError((error: HttpErrorResponse) => {
        return of({});
        //return throwError(() => error);
      }));
  }

  public getWelcomePage() {
    const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');

    return this.httpClient.get(`${this.baseUrl}/oasis/poc1/hello-oasis`,  { headers, responseType: 'text'})
    .pipe(
     map((data: any) => {
       return data;
     }),
     catchError((error: HttpErrorResponse) => {
       return throwError(() => error);
     }));
  }

  public getToken() {
    const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');

    return this.httpClient.get(`${this.baseUrl}/oasis/poc1/getTokenAPI`,  { headers, responseType: 'json'})
    .pipe(
     map((data: any) => {
       return data;
     }),
     catchError((error: HttpErrorResponse) => {
       return throwError(() => error);
     }));
  }

  public getQueryAllWell() {
    const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');

    return this.httpClient.get(`${this.baseUrl}/oasis/poc1/getQueryAllWells`,  { headers, responseType: 'json'})
    .pipe(
     map((data: any) => {
       return data;
     }),
     catchError((error: HttpErrorResponse) => {
       return throwError(() => error);
     }));
  }
}
