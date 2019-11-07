import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager } from 'ng-jhipster';

import { IEntities } from 'app/shared/model/entities.model';
import { AccountService } from 'app/core/auth/account.service';
import { EntitiesService } from './entities.service';

@Component({
  selector: 'jhi-entities',
  templateUrl: './entities.component.html'
})
export class EntitiesComponent implements OnInit, OnDestroy {
  entities: IEntities[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected entitiesService: EntitiesService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.entitiesService
      .query()
      .pipe(
        filter((res: HttpResponse<IEntities[]>) => res.ok),
        map((res: HttpResponse<IEntities[]>) => res.body)
      )
      .subscribe((res: IEntities[]) => {
        this.entities = res;
      });
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().subscribe(account => {
      this.currentAccount = account;
    });
    this.registerChangeInEntities();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IEntities) {
    return item.id;
  }

  registerChangeInEntities() {
    this.eventSubscriber = this.eventManager.subscribe('entitiesListModification', response => this.loadAll());
  }
}
