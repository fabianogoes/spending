import { Component, OnInit } from '@angular/core';
import { Type } from './type';
import { TypeService } from './type.service';

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

  constructor(private service: TypeService) {
  }

  ngOnInit() {
    this.onList();
  }

  public onSave() {
    console.log(`onSave(${this.type})...`);
    this.service.save(this.type)
      .subscribe(responseSave => {
        this.onFind(responseSave.id);
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
      this.pattern = null;
    });

  }

  public onNew() {
    console.log('onNew()...');
    this.enableEdit = true;
    this.type = new Type();
  }

  public onList() {
    console.log('onList()...');
    this.enableEdit = false;
    this.service.findAll().subscribe(data => this.types = data);
  }

  public onAddPattern(){
    console.log(this.type.patterns.indexOf(this.pattern));
    const index = this.type.patterns.indexOf(this.pattern);
    if (index > -1) { return; }
    this.type.patterns.push(this.pattern);
    if (this.type.id) {
      this.onSave();
    }
    this.pattern = null;
  }

  public onDeletePattern(pattern: string) {
    console.log(`onDeletePattern(${this.type.id}, ${pattern})`);
    this.service.deletePattern(this.type.id, pattern).subscribe(responseDelete => {
      this.onFind(this.type.id);
    });
  }

  public onRefresh() {
    if (this.enableEdit) {
      this.onFind(this.type.id);
    } else {
      this.onList();
    }
  }

}
