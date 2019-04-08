import {Component, Inject, OnInit} from '@angular/core';
import {EncuestaService} from '../../../../_services/encuesta.service';
import {MAT_DIALOG_DATA} from '@angular/material';
import {Lenguaje} from "../../../../_model/Lenguaje";

@Component({
  selector: 'app-nuevo',
  templateUrl: './editar.component.html',
  styleUrls: ['./editar.component.css']
})
export class EditarComponent implements OnInit {

  // lenguajes: Lenguaje[] = [
  //   {value: 'Java', viewValue: 'Java'},
  //   {value: 'C#', viewValue: 'C#'}
  // ];

  constructor(
    private serviceEncuesta: EncuestaService,
    @Inject(MAT_DIALOG_DATA) public data : any) {
  }

  ngOnInit() {
  }

  onSubmit() {
    this.data.encuesta.fecha = new Date();
    // console.log('registroRRRR: ' + JSON.stringify(this.data));
    this.serviceEncuesta.editarEncuesta(this.data.encuesta.id, this.data.encuesta).subscribe((data)=>{
      this.serviceEncuesta.mensajeRegistro.next('Registrado Correctamente...');
    }, (error) => {
      this.serviceEncuesta.mensajeRegistro.next('Error al guardar el encuesta...');
    });
  }

}
