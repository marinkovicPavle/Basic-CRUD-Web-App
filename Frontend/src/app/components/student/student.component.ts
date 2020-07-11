import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { StudentService } from 'src/app/services/student.service';

import { HttpClient } from '@angular/common/http';
import { MatTableDataSource } from '@angular/material/table';
import { Student } from 'src/app/models/student';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  displayedColumns = ['id', 'ime', 'prezime', 'brojIndeksa', 'grupa', 'projekat', 'actions'];
  dataSource: MatTableDataSource<Student>;

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;


  constructor(private studentService: StudentService) { }

  ngOnInit(): void {
    console.log('Inicijalizacija Student komponente!');
    this.loadData();
  }

  public loadData() {
    this.studentService.getAllStudent().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);

      // pretraga po nazivu ugnježdenog objekta
      this.dataSource.filterPredicate = (data, filter: string) => {
        const accumulator = (currentTerm, key) => {
          if(key==='grupa'){
            return currentTerm + data.grupa.oznaka;
          } else if(key==='projekat') {
            return currentTerm + data.projekat.naziv;
          } else {
            return currentTerm + data[key];
          }
        };
        const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
        const transformedFilter = filter.trim().toLowerCase();
        return dataStr.indexOf(transformedFilter) !== -1;
      };

       // sortiranje po nazivu ugnježdenog objekta
      this.dataSource.sortingDataAccessor = (data, property) => {
        switch (property) {
          case 'grupa': return data.grupa.oznaka.toLocaleLowerCase();
          case 'projekat': return data.projekat.naziv.toLocaleLowerCase();
          default: return data[property];
        }
      };

     /* this.dataSource.filterPredicate = (data, filter: string) => {
        const accumulator = (currentTerm, key) => {
          return key === 'projekat' ? currentTerm + data.projekat.naziv : currentTerm + data[key];
        };
        const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
        const transformedFilter = filter.trim().toLowerCase();
        return dataStr.indexOf(transformedFilter) !== -1;
      };

       // sortiranje po nazivu ugnježdenog objekta
      this.dataSource.sortingDataAccessor = (data, property) => {
        switch (property) {
          case 'projekat': return data.projekat.naziv.toLocaleLowerCase();
          default: return data[property];
        }
      };*/

      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });

  }

  applyFilter(filterValue: string){
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue;
  }

}
