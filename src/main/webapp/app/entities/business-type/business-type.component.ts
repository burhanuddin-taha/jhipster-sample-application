import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager } from 'ng-jhipster';

import { IBusinessType } from 'app/shared/model/business-type.model';
import { AccountService } from 'app/core/auth/account.service';
import { BusinessTypeService } from './business-type.service';

@Component({
  selector: 'jhi-business-type',
  templateUrl: './business-type.component.html'
})
export class BusinessTypeComponent implements OnInit, OnDestroy {
  businessTypes: IBusinessType[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected businessTypeService: BusinessTypeService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.businessTypeService
      .query()
      .pipe(
        filter((res: HttpResponse<IBusinessType[]>) => res.ok),
        map((res: HttpResponse<IBusinessType[]>) => res.body)
      )
      .subscribe((res: IBusinessType[]) => {
        this.businessTypes = res;
      });
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().subscribe(account => {
      this.currentAccount = account;
    });
    this.registerChangeInBusinessTypes();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IBusinessType) {
    return item.id;
  }

  registerChangeInBusinessTypes() {
    this.eventSubscriber = this.eventManager.subscribe('businessTypeListModification', response => this.loadAll());
  }
}
