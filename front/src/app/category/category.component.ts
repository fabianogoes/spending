import { CategoryServiceService } from './category-service.service';
import { Component, OnInit } from '@angular/core';
import { Category } from './category';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styles: []
})
export class CategoryComponent implements OnInit {

  private category: Category;
  public enableEdit: Boolean = false;
  public categories: Array<Category>;

  constructor(private service: CategoryServiceService) {
  }

  ngOnInit() {
    this.enableEdit = false;
    this.category = new Category();
    this.service.findAll().subscribe(data => this.categories = data);
  }

  public onSave({ value, valid}: { value: Category, valid: boolean }) {
    this.service.save(value)
      .subscribe(responseSave => {
        this.service.findAll().subscribe(data => this.categories = data);
        this.enableEdit = false;
      });
    this.category = new Category();
  }

  public onDelete(categoryId) {
    console.log(`onDelete(${categoryId})...`);
    this.service.delete(categoryId).subscribe(responseDelete => {
      this.service.findAll().subscribe(data => this.categories = data);
      this.enableEdit = false;
    });
    this.category = new Category();
  }

  public onFind(categoryId) {
    console.log(`onFind(${categoryId})...`);
    this.service.find(categoryId).subscribe(responseFind => {
      this.category = responseFind;
      this.enableEdit = true;
    });

  }

  public onNew() {
    this.enableEdit = true;
    this.category = new Category();
  }

}
