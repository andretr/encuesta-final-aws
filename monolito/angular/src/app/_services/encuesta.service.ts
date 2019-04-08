import { Injectable } from '@angular/core';
import { Encuesta } from '../_model/Encuesta';
import { HttpClient } from '@angular/common/http';
import { HOST_BACKEND, TOKEN_NAME } from '../_shared/constants';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EncuestaService {

  urlFeedback: string = `${HOST_BACKEND}/api/encuesta`;

  mensajeRegistro = new Subject<string>();

  constructor(private httpClient: HttpClient) { }

  obtenerEncuesta(id: number) {
    return this.httpClient.get<Encuesta>(`${this.urlFeedback}/listar/${id}`);
  }
  obtenerListaEncuestas() {
    return this.httpClient.get<Encuesta[]>(`${this.urlFeedback}/listar`);
  }

  obtenerEncuestaPropios(page: number, size: number) {
    return this.httpClient.get<Encuesta[]>(`${this.urlFeedback}/listar?page=${page}&size=${size}`);
  }

  guardarEncuesta(encuesta: Encuesta) {
    return this.httpClient.post(`${this.urlFeedback}/registrar`, encuesta);
  }

  editarEncuesta(id: number, encuesta: Encuesta) {
    return this.httpClient.put(`${this.urlFeedback}/editar/${id}`, encuesta);
  }
}
