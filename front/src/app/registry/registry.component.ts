import { Category } from './../category/category';
import { RegistryService } from './regitry.service';
import { Component, OnInit } from '@angular/core';
import { Registry } from './registry';
import { Type } from '../type/type';

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

  constructor(private service: RegistryService) { }

  ngOnInit() {
    this.onList();
  }

  public onList() {
    console.log('onList()...');
    this.enableEdit = false;
    this.service.findAll().subscribe(data => this.registries = data);
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
