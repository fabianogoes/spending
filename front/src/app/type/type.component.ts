import { Component, OnInit } from '@angular/core';
import { Type } from './type';
import { TypeServiceService } from './type-service.service';

@Component({
  selector: 'app-type',
  templateUrl: './type.component.html',
  styles: []
})
export class TypeComponent implements OnInit {

  public type: Type;
  public enableEdit: Boolean = true;
  public types: Array<Type>;
  public pattern: string;

  constructor(private service: TypeServiceService) {
  }

  ngOnInit() {
    this.enableEdit = true;
    this.type = new Type();
    this.service.findAll().subscribe(data => this.types = data);
  }

  public onSave({ value, valid}: { value: Type, valid: boolean }) {
    value.patterns = this.type.patterns;
    this.service.save(value)
      .subscribe(responseSave => {
        this.service.findAll().subscribe(data => this.types = data);
        this.enableEdit = false;
      });
  }

  public onDelete(typeId) {
    console.log(`onDelete(${typeId})...`);
    this.service.delete(typeId).subscribe(responseDelete => {
      this.service.findAll().subscribe(data => this.types = data);
      this.enableEdit = false;
    });
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

  public onAddPattern(){
    this.type.patterns.push(this.pattern);
    this.pattern = null;
    console.log(this.type.patterns);
  }

}
