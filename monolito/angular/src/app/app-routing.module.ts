import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EncuestaComponent} from './pages/encuesta/encuesta.component';
import {SecurityComponent} from './pages/security/security.component';
import {GuardService} from './_services/guard.service';
import {LogoutComponent} from './pages/logout/logout.component';
import {AdminComponent} from './pages/admin/admin/admin.component';
import {BodyComponent} from './pages/body/body.component';
import {LoginComponent} from './pages/login/login.component';
import {ResultadosComponent} from "./pages/admin/resultados/resultados.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent, canActivate: [GuardService]},
  {path: 'logout', component: LogoutComponent},
  {path: 'security', component: SecurityComponent},
  {path: 'app', component: BodyComponent, children: [

      {path: 'encuesta', component: EncuestaComponent},
      {path: 'admin', component: AdminComponent, children: [
          {path: 'resultados', component: ResultadosComponent},
        ]},
    ], canActivate: [GuardService]},
  {path: '**', redirectTo: 'login', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
