import { Category } from './../category/category';
import { RegistryService } from './regitry.service';
import { Component, OnInit } from '@angular/core';
import { Registry } from './registry';
import { Type } from '../type/type';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-registry',
  templateUrl: './registry.component.html',
  styles: []
})
export class RegistryComponent implements OnInit {

  public registry: Registry;
  public enableEdit: Boolean = true;
  public registries: Array<Registry>;
  public types: Array<Type>;
  public categories: Array<Category>;
  public registriesLength: number;
  private categoryId: string;
  private typeId: string;

  constructor(private service: RegistryService, private route: ActivatedRoute) {
    this.route.params.subscribe( params => {
      this.categoryId = params['categoryId'];
      this.typeId = params['typeId'];
    });
  }

  ngOnInit() {
    this.onList();
  }

  public onList() {
    console.log(`onList(${this.categoryId})...`);
    this.enableEdit = false;

    if (this.categoryId){
      this.service.findByCategory(this.categoryId).subscribe(data => {
        this.registries = data;
        this.registriesLength = this.registries.length;
      });
    } else if (this.typeId) {
      this.service.findByType(this.typeId).subscribe(data => {
        this.registries = data;
        this.registriesLength = this.registries.length;
      });
    } else {
      this.service.findAll().subscribe(data => {
        this.registries = data;
        this.registriesLength = this.registries.length;
      });
    }

    this.service.findAllCategories().subscribe(data => this.categories = data);
    this.service.findAllTypes().subscribe(data => this.types = data);
  }

  onSave() {
    console.log(`onSave()...`);
    this.service.save(this.registry)
      .subscribe(responseSave => {
        this.onFind(responseSave.id);
      });
  }

  public onDelete(registryId) {
    console.log(`onDelete(${registryId})...`);
    this.service.delete(registryId).subscribe(responseDelete => {
      this.service.findAll().subscribe(data => this.registries = data);
      this.enableEdit = false;
    });
    this.registry = new Registry();
  }

  public onFind(categoryId) {
    console.log(`onFind(${categoryId})...`);
    this.service.find(categoryId).subscribe(responseFind => {
      this.registry = responseFind;
      this.enableEdit = true;
    });
  }

  public onNew() {
    this.enableEdit = true;
    this.registry = new Registry();
  }

  public onRefresh() {
    if (this.enableEdit) {
      this.onFind(this.registry.id);
    } else {
      this.onList();
    }
  }

}
