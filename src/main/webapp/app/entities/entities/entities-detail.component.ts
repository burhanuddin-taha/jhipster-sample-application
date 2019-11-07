import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEntities } from 'app/shared/model/entities.model';

@Component({
  selector: 'jhi-entities-detail',
  templateUrl: './entities-detail.component.html'
})
export class EntitiesDetailComponent implements OnInit {
  entities: IEntities;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ entities }) => {
      this.entities = entities;
    });
  }

  previousState() {
    window.history.back();
  }
}
