import { Component, OnInit } from '@angular/core';
import {Encuesta} from "../../_model/Encuesta";
import {Lenguaje} from "../../_model/Lenguaje";
import {EncuestaService} from "../../_services/encuesta.service";
import {MatSnackBar} from "@angular/material";

@Component({
  selector: 'app-about',
  templateUrl: './encuesta.component.html',
  styleUrls: ['./encuesta.component.css']
})
export class EncuestaComponent implements OnInit {


  encuesta: Encuesta;
  lenguajes: Lenguaje[] = [
    {value: 'Java', viewValue: 'Java'},
    {value: 'C#', viewValue: 'C#'}
  ];

  constructor(
    private snackBar: MatSnackBar,
    private serviceEncuesta: EncuestaService) {
    this.encuesta = new Encuesta();
  }

  ngOnInit() {
    this.serviceEncuesta.mensajeRegistro.subscribe((dato) => {
      this.snackBar.open(dato, null, {
        duration: 1500,
      });

    });
  }

  onSubmit() {
    this.encuesta.fecha = new Date();
    this.serviceEncuesta.guardarEncuesta(this.encuesta).subscribe((data)=>{
      this.serviceEncuesta.mensajeRegistro.next('Registrado Correctamente...');
    }, (error) => {
      this.serviceEncuesta.mensajeRegistro.next('Error al guardar el encuesta...');
    });
  }

}
