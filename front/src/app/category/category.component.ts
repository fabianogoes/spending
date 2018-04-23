import { CategoryService } from './category.service';
import { Component, OnInit } from '@angular/core';
import { Category } from './category';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styles: []
})
export class CategoryComponent implements OnInit {

  public category: Category;
  public enableEdit: Boolean = false;
  public categories: Array<Category>;
  public pattern: string;

  constructor(private service: CategoryService) {
  }

  ngOnInit() {
    this.onList();
  }

  public onList() {
    this.enableEdit = false;
    this.category = new Category();
    this.service.findAll().subscribe(data => this.categories = data);
  }

  public onSave() {
    console.log(`onSave(${this.category})...`);
    this.service.save(this.category)
      .subscribe(responseSave => {
        this.onFind(responseSave.id);
      });
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

  public onRefresh() {
    if (this.enableEdit) {
      this.onFind(this.category.id);
    } else {
      this.onList();
    }
  }

  public onAddPattern() {
    console.log(this.category.patterns.indexOf(this.pattern));
    const index = this.category.patterns.indexOf(this.pattern);
    if (index > -1) { return; }
    this.category.patterns.push(this.pattern);
    if (this.category.id) {
      this.onSave();
    }
    this.pattern = null;
  }

  public onDeletePattern(pattern: string) {
    console.log(`onDeletePattern(${this.category.id}, ${pattern})`);
    this.service.deletePattern(this.category.id, pattern).subscribe(responseDelete => {
      this.onFind(this.category.id);
    });
  }

}
