import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';
import { CONST_ROUTING } from './app.routing';

import { AppComponent } from './app.component';
import { MenuComponent } from './menu.component';
import { DashComponent } from './dash.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    DashComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    FormsModule,
    HttpModule,
    CONST_ROUTING
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
