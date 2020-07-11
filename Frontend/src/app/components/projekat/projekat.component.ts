import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { MatTableDataSource } from '@angular/material/table';
import { Projekat } from 'src/app/models/projekat';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatDialog } from '@angular/material/dialog';
import { ProjekatService } from 'src/app/services/projekat.service';

@Component({
  selector: 'app-projekat',
  templateUrl: './projekat.component.html',
  styleUrls: ['./projekat.component.css']
})
export class ProjekatComponent implements OnInit {

  displayedColumns = ['id', 'naziv', 'oznaka', 'opis', 'actions'];
  dataSource: MatTableDataSource<Projekat>;

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;


  constructor(private projekatService: ProjekatService) { }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData() {
    this.projekatService.getAllProjekat().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);

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
