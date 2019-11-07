import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEntityAttributes } from 'app/shared/model/entity-attributes.model';

@Component({
  selector: 'jhi-entity-attributes-detail',
  templateUrl: './entity-attributes-detail.component.html'
})
export class EntityAttributesDetailComponent implements OnInit {
  entityAttributes: IEntityAttributes;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ entityAttributes }) => {
      this.entityAttributes = entityAttributes;
    });
  }

  previousState() {
    window.history.back();
  }
}
