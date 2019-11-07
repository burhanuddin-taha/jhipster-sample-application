import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEntityStructure } from 'app/shared/model/entity-structure.model';

@Component({
  selector: 'jhi-entity-structure-detail',
  templateUrl: './entity-structure-detail.component.html'
})
export class EntityStructureDetailComponent implements OnInit {
  entityStructure: IEntityStructure;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ entityStructure }) => {
      this.entityStructure = entityStructure;
    });
  }

  previousState() {
    window.history.back();
  }
}
