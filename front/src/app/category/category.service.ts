import { Category } from './category';
import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';

@Injectable()
export class CategoryService {

  private url = 'http://localhost:8080/categories';

  constructor(private http: Http) { }

  findAll(): Observable<Category[]> {
    return this.http.get(this.url)
      .map((response: Response) => <Category[]>response.json())
      .catch(this.handleError);
  }

  save(category: Category): Observable<Category> {
    return this.http.post(this.url, category)
      .map((response: Response) => <Category>response.json())
      .catch(this.handleError);
  }

  delete(categoryId: string): Observable<any> {
    const urlDelete = `${this.url}/${categoryId}`;
    return this.http.delete(urlDelete)
      .catch(this.handleError);
  }

  find(categoryId: string): Observable<Category> {
    const urlFind = `${this.url}/${categoryId}`;
    return this.http.get(urlFind)
      .map((response: Response) => <Category>response.json())
      .catch(this.handleError);
  }

  deletePattern(typeId: string, pattern: string): Observable<any> {
    const urlDelete = `${this.url}/${typeId}/pattern/${pattern}`;
    return this.http.delete(urlDelete)
      .catch(this.handleError);
  }

  private handleError(error: Response) {
    console.error(error);
    return Observable.throw(error.json().error || 'Server error');
  }

}
