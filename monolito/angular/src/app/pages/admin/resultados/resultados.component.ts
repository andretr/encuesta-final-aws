import {Component, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatPaginator, MatSnackBar, MatTableDataSource} from '@angular/material';
import {EditarComponent} from './editar/editar.component';
import {EncuestaService} from "../../../_services/encuesta.service";
import {Encuesta} from "../../../_model/Encuesta";

@Component({
  selector: 'app-encuesta',
  templateUrl: './resultados.component.html',
  styleUrls: ['./resultados.component.css']
})
export class ResultadosComponent implements OnInit {

  dataSource:MatTableDataSource<Encuesta>;
  totalElementos: number = 0;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  displayedColumns: string[] = ['id', 'nombres', 'apellidos', 'edad', 'lenguaje','fecha', 'acciones'];
  constructor(
    private dialog: MatDialog,
    private serviceEncuesta: EncuestaService,
    private snackBar: MatSnackBar) {
    this.dataSource = new MatTableDataSource<Encuesta>();

  }

  ngOnInit() {
    this.cargarTabla(0, 100, false);

    this.serviceEncuesta.mensajeRegistro.subscribe((dato) => {
      this.dialog.closeAll();
      this.snackBar.open(dato, null, {
        duration: 1500,
      });
      this.cargarTabla(0, 100, false);
    });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  mostrarMas(event){
    this.cargarTabla(event.pageIndex, event.pageSize, true);
  }

  cargarTabla(pageIndex: number, pageSize: number, desdePaginador: boolean){

    this.serviceEncuesta.obtenerEncuestaPropios(pageIndex, pageSize).subscribe((datos) => {
      let encuestas = JSON.parse(JSON.stringify(datos)).content;
      this.dataSource = new MatTableDataSource<Encuesta>(encuestas);
      this.totalElementos = JSON.parse(JSON.stringify(datos)).totalElements;
      if(!desdePaginador){
        this.dataSource.paginator = this.paginator;
      }
    });
  }


  openDialog(encuesta: Encuesta) {

    this.dialog.open(EditarComponent, {
      width: '80%',
      height: '80%',
      data: {
        encuesta: encuesta
      }
    });
  }

}
