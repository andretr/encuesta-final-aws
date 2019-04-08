import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {MaterialModule} from './material/material.module';

import {MatRadioModule} from '@angular/material/radio';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {EncuestaComponent} from './pages/encuesta/encuesta.component';
import {EditarComponent} from './pages/admin/resultados/editar/editar.component';
import {SecurityComponent} from './pages/security/security.component';
import {LogoutComponent} from './pages/logout/logout.component';
import {TokenInterceptorService} from './_services/token-interceptor.service';
import {AdminComponent} from './pages/admin/admin/admin.component';

import {LocationStrategy, PathLocationStrategy} from '@angular/common';
import {BodyComponent} from './pages/body/body.component';
import {LoginComponent} from './pages/login/login.component';
import {ErrorComponent} from './pages/login/error/error.component';
import {ResultadosComponent} from "./pages/admin/resultados/resultados.component";

@NgModule({
  declarations: [
    AppComponent,
    EncuestaComponent,
    ResultadosComponent,
    EditarComponent,
    SecurityComponent,
    LogoutComponent,
    AdminComponent,
    BodyComponent,
    LoginComponent,
    ErrorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    HttpClientModule,
    MatRadioModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    },
    { provide: LocationStrategy, useClass: PathLocationStrategy }],
  bootstrap: [AppComponent],
  entryComponents: [
    EditarComponent,
    ErrorComponent]
})
export class AppModule { }
