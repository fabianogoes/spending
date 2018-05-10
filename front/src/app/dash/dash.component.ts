import { RankingByType } from './ranking-by-type';
import { DashService } from './dash.service';
import { Component, OnInit } from '@angular/core';
import { RankingByCategory } from './ranking-by-category';

@Component({
  selector: 'app-dash',
  templateUrl: './dash.component.html',
  styles: []
})
export class DashComponent implements OnInit {

  public rankingByCategory: RankingByCategory[];
  public rankingByType: RankingByType[];

  constructor(private service: DashService) { }

  ngOnInit() {
    this.service.getRankingByCategory().subscribe(data => this.rankingByCategory = data);
    this.service.getRankingByType().subscribe(data => this.rankingByType = data);
  }

}
