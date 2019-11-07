import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBusinessType } from 'app/shared/model/business-type.model';

@Component({
  selector: 'jhi-business-type-detail',
  templateUrl: './business-type-detail.component.html'
})
export class BusinessTypeDetailComponent implements OnInit {
  businessType: IBusinessType;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ businessType }) => {
      this.businessType = businessType;
    });
  }

  previousState() {
    window.history.back();
  }
}
