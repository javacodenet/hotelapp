import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';

import {AppComponent} from './app.component';
import {SharedMaterialModuleModule} from "./shared-material-module/shared-material-module.module";
import {NavBarComponent} from './components/nav-bar/nav-bar.component';
import { LoginComponent } from './components/login/login.component';
import {HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from './app-routing/app-routing.module';
import {LoginService} from './components/login/login.service';
import {FormsModule} from '@angular/forms';
import {EventManagerService} from './shared/event-manager.service';
import {AddGuestComponent} from './components/add-guest/add-guest.component';
import {EditGuestComponent} from './components/edit-guest/edit-guest.component';
import {GuestListComponent} from './components/guest-list/guest-list.component';
import {UploadFileService} from './shared/upload-file/upload-file.service';
import {AddGuestService} from './components/add-guest/add-guest.service';


@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    LoginComponent,
    AddGuestComponent,
    EditGuestComponent,
    GuestListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    SharedMaterialModuleModule,
    NoopAnimationsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [LoginService, EventManagerService, UploadFileService, AddGuestService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
