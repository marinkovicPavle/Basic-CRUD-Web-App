import { Component, OnInit, ViewChild, ChangeDetectorRef, Input } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { MatTableDataSource } from '@angular/material/table';
import { Grupa } from 'src/app/models/grupa';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatDialog } from '@angular/material/dialog';
import { GrupaService } from 'src/app/services/grupa.service';
import { Smer } from 'src/app/models/smer';

@Component({
  selector: 'app-grupa',
  templateUrl: './grupa.component.html',
  styleUrls: ['./grupa.component.css']
})
export class GrupaComponent implements OnInit {

  displayedColumns = ['id', 'oznaka', 'smer', 'actions'];
  dataSource: MatTableDataSource<Grupa>;

  @Input() selektovanSmer: Smer;

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;

  selektovanaGrupa: Grupa;

  constructor(private grupaService: GrupaService) { }

  ngOnInit(): void {

  }

  ngOnChanges() {
    if (this.selektovanSmer.id) {
      debugger;
      this.loadData();
    }
  }

  public loadData() {
    this.grupaService.grupeZaSmerId(this.selektovanSmer.id).subscribe(data => {
      this.dataSource = new MatTableDataSource(data);

      // pretraga po nazivu ugnježdenog objekta
      this.dataSource.filterPredicate = (data, filter: string) => {
        const accumulator = (currentTerm, key) => {
          return key === 'smer' ? currentTerm + data.smer.naziv : currentTerm + data[key];
        };
        const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
        const transformedFilter = filter.trim().toLowerCase();
        return dataStr.indexOf(transformedFilter) !== -1;
      };

       // sortiranje po nazivu ugnježdenog objekta
      this.dataSource.sortingDataAccessor = (data, property) => {
        switch (property) {
          case 'smer': return data.smer.naziv.toLocaleLowerCase();
          default: return data[property];
        }
      };

      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });

  }

  selectRow(row: any){
    this.selektovanaGrupa = row;
  }

  applyFilter(filterValue: string){
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue;
  }

}
