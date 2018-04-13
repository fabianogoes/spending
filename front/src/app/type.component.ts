import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-type',
  templateUrl: './type.component.html',
  styles: []
})
export class TypeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    localStorage.setItem('activeModule', 'type');
  }

}
