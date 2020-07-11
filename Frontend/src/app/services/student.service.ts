import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Student } from '../models/student';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  private readonly API_URL = 'http://localhost:8083/student/';

  dataChange: BehaviorSubject<Student[]> = new BehaviorSubject<Student[]>([]);

  constructor(private httpClient: HttpClient) {}

  public getAllStudent(): Observable<Student[]> {
    this.httpClient.get<Student[]>(this.API_URL).subscribe(
      (data) => {
        this.dataChange.next(data);
      },
      (error: HttpErrorResponse) => {
        console.log(error.name + ' ' + error.message);
      }
    );

    return this.dataChange.asObservable();
  }

  public addStudent(student: Student): void {
    this.httpClient.post(this.API_URL, student);
  }

  public updateStudent(student: Student): void {
    this.httpClient.put(this.API_URL, student);
  }

  public deleteStudent(id: number): void {
    console.log(this.API_URL + id);
    this.httpClient.delete(this.API_URL + id);
  }
}
