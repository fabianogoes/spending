import { RankingByCategory } from './ranking-by-category';
import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { RankingByType } from './ranking-by-type';

@Injectable()
export class DashService {

  private url = 'http://localhost:8080/ranking';

  constructor(private http: Http) { }

  getRankingByCategory(): Observable<RankingByCategory[]> {
    return this.http.get(`${this.url}/top/category/3`)
      .map((response: Response) => <RankingByCategory[]>response.json())
      .catch(this.handleError);
  }

  getRankingByType(): Observable<RankingByType[]> {
    return this.http.get(`${this.url}/top/type/3`)
      .map((response: Response) => <RankingByCategory[]>response.json())
      .catch(this.handleError);
  }

  private handleError(error: Response) {
    console.error(error);
    return Observable.throw(error.json().error || 'Server error');
  }

}
