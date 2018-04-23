import { Category } from './../category/category';
import { Registry } from './registry';
import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import { CategoryService } from '../category/category.service';
import { TypeService } from '../type/type.service';
import { Type } from '../type/type';

@Injectable()
export class RegistryService {

  private url = 'http://localhost:8080/registries';

  constructor(private http: Http,
              private categoryService: CategoryService,
              private typeService: TypeService) { }

  findAll(): Observable<Registry[]> {
    return this.http.get(this.url)
      .map((response: Response) => <Registry[]>response.json())
      .catch(this.handleError);
  }

  findAllCategories(): Observable<Category[]> {
    const url = 'http://localhost:8080/categories';
    return this.http.get(url)
      .map((response: Response) => <Category[]>response.json())
      .catch(this.handleError);
  }

  findAllTypes(): Observable<Type[]> {
    const url = 'http://localhost:8080/types';
    return this.http.get(url)
      .map((response: Response) => <Type[]>response.json())
      .catch(this.handleError);
  }

  save(registry: Registry): Observable<Registry> {
    console.log(`${registry}`);
    return null;
    // return this.http.post(this.url, registry)
    //   .map((response: Response) => <Registry>response.json())
    //   .catch(this.handleError);
  }

  delete(registryId: string): Observable<any> {
    const urlDelete = `${this.url}/${registryId}`;
    return this.http.delete(urlDelete)
      .catch(this.handleError);
  }

  find(registryId: string): Observable<Registry> {
    const urlFind = `${this.url}/${registryId}`;
    return this.http.get(urlFind)
      .map((response: Response) => <Registry>response.json())
      .catch(this.handleError);
  }

  private handleError(error: Response) {
    console.error(error);
    return Observable.throw(error.json().error || 'Server error');
  }

}
