import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Grupa } from '../models/grupa';

@Injectable({
  providedIn: 'root',
})
export class GrupaService {

  private readonly API_URL = 'http://localhost:8083/grupa/';
  private readonly API_URL_BYID = 'http://localhost:8083/grupeZaSmerId/';

  dataChange: BehaviorSubject<Grupa[]> = new BehaviorSubject<Grupa[]>([]);

  constructor(private httpClient: HttpClient) {}

  public grupeZaSmerId(idSmera: number): Observable<Grupa[]> {
    this.httpClient.get<Grupa[]>(this.API_URL_BYID + idSmera).subscribe(
      (data) => {
        this.dataChange.next(data);
      },
      (error: HttpErrorResponse) => {
        console.log(error.name + ' ' + error.message);
      }
    );
    return this.dataChange.asObservable();
  }

  public addGrupa(grupa: Grupa): void {
    this.httpClient.post(this.API_URL, grupa).subscribe();
  }

  public updateGrupa(grupa: Grupa): void {
    this.httpClient.put(this.API_URL, grupa).subscribe();
  }

  public deleteGrupa(id: number): void {
    this.httpClient.delete(this.API_URL + id).subscribe();
  }
}
