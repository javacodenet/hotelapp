import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {LoginComponent} from '../components/login/login.component';


const routes = [{
  path: '',
  redirectTo: '/login',
  pathMatch: 'full'
},
  {
    path: 'login',
    component: LoginComponent
  }];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  declarations: [],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
