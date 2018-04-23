import { Type } from './type';
import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';

@Injectable()
export class TypeServiceService {

  private url = 'http://localhost:8080/types';

  constructor(private http: Http) { }

  findAll(): Observable<Type[]> {
    return this.http.get(this.url)
      .map((response: Response) => <Type[]>response.json())
      .catch(this.handleError);
  }

  save(type: Type): Observable<Type> {
    return this.http.post(this.url, type)
      .map((response: Response) => <Type>response.json())
      .catch(this.handleError);
  }

  delete(typeId: string): Observable<any> {
    const urlDelete = `${this.url}/${typeId}`;
    return this.http.delete(urlDelete)
      .catch(this.handleError);
  }

  find(typeId: string): Observable<Type> {
    const urlFind = `${this.url}/${typeId}`;
    return this.http.get(urlFind)
      .map((response: Response) => <Type>response.json())
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
