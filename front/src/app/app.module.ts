import { CategoryService } from './category/category.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';
import { CONST_ROUTING } from './app.routing';


import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { DashComponent } from './dash/dash.component';
import { RegistryComponent } from './registry/registry.component';
import { CategoryComponent } from './category/category.component';
import { TypeComponent } from './type/type.component';
import { TypeService } from './type/type.service';
import { UploadComponent } from './upload/upload.component';
import { UploadService } from './upload/upload.service';
import { RegistryService } from './registry/regitry.service';
import { DashService } from './dash/dash.service';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    DashComponent,
    RegistryComponent,
    CategoryComponent,
    TypeComponent,
    UploadComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule,
    FormsModule,
    CONST_ROUTING
  ],
  providers: [
    TypeService,
    CategoryService,
    UploadService,
    RegistryService,
    DashService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
