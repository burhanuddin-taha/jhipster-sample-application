import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEntityAuthority } from 'app/shared/model/entity-authority.model';

@Component({
  selector: 'jhi-entity-authority-detail',
  templateUrl: './entity-authority-detail.component.html'
})
export class EntityAuthorityDetailComponent implements OnInit {
  entityAuthority: IEntityAuthority;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ entityAuthority }) => {
      this.entityAuthority = entityAuthority;
    });
  }

  previousState() {
    window.history.back();
  }
}
