import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';

import {AppComponent} from './app.component';
import {SharedMaterialModuleModule} from "./shared-material-module/shared-material-module.module";
import {NavBarComponent} from './components/nav-bar/nav-bar.component';
import { LoginComponent } from './components/login/login.component';
import {HttpClientModule} from "@angular/common/http";


@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    SharedMaterialModuleModule,
    NoopAnimationsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
