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

  getTypesAll(): Observable<Type[]> {
    return this.http.get(this.url)
      .map((response: Response) => <Type[]>response.json())
      // .do(data => console.log(JSON.stringify(data)))
      .catch(this.handleError);
  }

  private handleError(error: Response) {
    console.error(error);
    return Observable.throw(error.json().error || 'Server error');
  }

}
