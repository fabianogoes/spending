import { Component, OnInit } from '@angular/core';
import { Type } from './type';
import { TypeServiceService } from './type-service.service';

@Component({
  selector: 'app-type',
  templateUrl: './type.component.html',
  styles: []
})
export class TypeComponent implements OnInit {

  private type: Type;
  public enableEdit: Boolean = false;
  public types: Array<Type>;

  constructor(private service: TypeServiceService) {

  }

  ngOnInit() {
    localStorage.setItem('activeModule', 'type');
    this.enableEdit = false;
    this.type = new Type();
    this.service.getTypesAll().subscribe(data => this.types = data);
  }

  public onSave({ value, valid}: { value: Type, valid: boolean }) {
    this.service.save(value)
      .subscribe(responseSave => {
        this.service.getTypesAll().subscribe(data => this.types = data);
        this.enableEdit = false;
      });
    this.type = new Type();
  }

  public onDelete(typeId) {
    console.log(`onDelete(${typeId})...`);
    this.service.delete(typeId).subscribe(responseDelete => {
      this.service.getTypesAll().subscribe(data => this.types = data);
      this.enableEdit = false;
    });
    this.type = new Type();
  }

  public onFind(typeId) {
    console.log(`onFind(${typeId})...`);
    this.service.find(typeId).subscribe(responseFind => {
      this.type = responseFind;
      this.enableEdit = true;
    });

  }

  public onNew() {
    this.enableEdit = true;
    this.type = new Type();
  }

}
