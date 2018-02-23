import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {LoginComponent} from '../components/login/login.component';
import {AddGuestComponent} from '../components/add-guest/add-guest.component';
import {GuestListComponent} from '../components/guest-list/guest-list.component';


const routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'addGuest',
    component: AddGuestComponent
  },
  {
    path: 'guestList',
    component: GuestListComponent
  }
];

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
