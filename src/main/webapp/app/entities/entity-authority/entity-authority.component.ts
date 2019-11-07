import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager } from 'ng-jhipster';

import { IEntityAuthority } from 'app/shared/model/entity-authority.model';
import { AccountService } from 'app/core/auth/account.service';
import { EntityAuthorityService } from './entity-authority.service';

@Component({
  selector: 'jhi-entity-authority',
  templateUrl: './entity-authority.component.html'
})
export class EntityAuthorityComponent implements OnInit, OnDestroy {
  entityAuthorities: IEntityAuthority[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected entityAuthorityService: EntityAuthorityService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.entityAuthorityService
      .query()
      .pipe(
        filter((res: HttpResponse<IEntityAuthority[]>) => res.ok),
        map((res: HttpResponse<IEntityAuthority[]>) => res.body)
      )
      .subscribe((res: IEntityAuthority[]) => {
        this.entityAuthorities = res;
      });
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().subscribe(account => {
      this.currentAccount = account;
    });
    this.registerChangeInEntityAuthorities();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IEntityAuthority) {
    return item.id;
  }

  registerChangeInEntityAuthorities() {
    this.eventSubscriber = this.eventManager.subscribe('entityAuthorityListModification', response => this.loadAll());
  }
}
