import { Component, OnInit } from '@angular/core';
import { Type } from './type';
import { TypeServiceService } from './type-service.service';

@Component({
  selector: 'app-type',
  templateUrl: './type.component.html',
  styles: []
})
export class TypeComponent implements OnInit {

  private id = 0;
  private type: Type;
  public types: Array<Type>;

  constructor(private service: TypeServiceService) {

  }

  ngOnInit() {
    localStorage.setItem('activeModule', 'type');
    this.type = new Type();
    this.service.getTypesAll().subscribe(data => this.types = data);
  }

  public onSave({ value, valid}: { value: Type, valid: boolean }) {
    const newType = new Type({
      id: String(++this.id),
      name: value.name,
      pattern: value.pattern
    });
    this.types.push(newType);
    this.type = new Type();
  }

}
