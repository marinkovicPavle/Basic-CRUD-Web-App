import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { MatTableDataSource } from '@angular/material/table';
import { Smer } from 'src/app/models/smer';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatDialog } from '@angular/material/dialog';
import { SmerService } from 'src/app/services/smer.service';

@Component({
  selector: 'app-smer',
  templateUrl: './smer.component.html',
  styleUrls: ['./smer.component.css']
})
export class SmerComponent implements OnInit {

  displayedColumns = ['id', 'naziv', 'oznaka', 'actions'];
  dataSource: MatTableDataSource<Smer>;
  selektovanSmer: Smer;

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;


  constructor(private smerServices: SmerService) { }

  ngOnInit(): void {
    this.loadData();
  }

  public loadData() {
    this.smerServices.getAllSmer().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);

      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  selectRow(row: any){
    this.selektovanSmer = row;
   }

  applyFilter(filterValue: string){
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue;
  }


}
