import { Component, OnInit, Input } from '@angular/core';
import { ProblemaService } from '../../../_services/problema.service';
import { Problema } from '../../../_model/Problema';
import { Encuesta } from '../../../_model/Encuesta';

@Component({
  selector: 'app-nuevo',
  templateUrl: './nuevo.component.html',
  styleUrls: ['./nuevo.component.css']
})
export class NuevoComponent implements OnInit {

  problemas: Problema[] = [];
  texto: string = '';
  feedback: Encuesta;

  constructor(
    private serviceProblema: ProblemaService) {
    this.feedback = new Encuesta();
  }

  ngOnInit() {
    this.serviceProblema.obtenerListaEncuestas().subscribe((data) => {
      this.problemas = data;
    });
  }

  onSubmit() {
    this.feedback.fecha = new Date();
    this.serviceProblema.guardarEncuesta(this.feedback).subscribe((data)=>{
        this.serviceProblema.mensajeRegistro.next('Registrado Correctamente...');
    }, (error) => {
      this.serviceProblema.mensajeRegistro.next('Error al guardar el feedback...');
    });
  }

}
