import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager } from 'ng-jhipster';

import { IEntityAttributes } from 'app/shared/model/entity-attributes.model';
import { AccountService } from 'app/core/auth/account.service';
import { EntityAttributesService } from './entity-attributes.service';

@Component({
  selector: 'jhi-entity-attributes',
  templateUrl: './entity-attributes.component.html'
})
export class EntityAttributesComponent implements OnInit, OnDestroy {
  entityAttributes: IEntityAttributes[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected entityAttributesService: EntityAttributesService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.entityAttributesService
      .query()
      .pipe(
        filter((res: HttpResponse<IEntityAttributes[]>) => res.ok),
        map((res: HttpResponse<IEntityAttributes[]>) => res.body)
      )
      .subscribe((res: IEntityAttributes[]) => {
        this.entityAttributes = res;
      });
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().subscribe(account => {
      this.currentAccount = account;
    });
    this.registerChangeInEntityAttributes();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IEntityAttributes) {
    return item.id;
  }

  registerChangeInEntityAttributes() {
    this.eventSubscriber = this.eventManager.subscribe('entityAttributesListModification', response => this.loadAll());
  }
}
