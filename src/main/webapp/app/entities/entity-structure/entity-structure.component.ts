import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager } from 'ng-jhipster';

import { IEntityStructure } from 'app/shared/model/entity-structure.model';
import { AccountService } from 'app/core/auth/account.service';
import { EntityStructureService } from './entity-structure.service';

@Component({
  selector: 'jhi-entity-structure',
  templateUrl: './entity-structure.component.html'
})
export class EntityStructureComponent implements OnInit, OnDestroy {
  entityStructures: IEntityStructure[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected entityStructureService: EntityStructureService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.entityStructureService
      .query()
      .pipe(
        filter((res: HttpResponse<IEntityStructure[]>) => res.ok),
        map((res: HttpResponse<IEntityStructure[]>) => res.body)
      )
      .subscribe((res: IEntityStructure[]) => {
        this.entityStructures = res;
      });
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().subscribe(account => {
      this.currentAccount = account;
    });
    this.registerChangeInEntityStructures();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IEntityStructure) {
    return item.id;
  }

  registerChangeInEntityStructures() {
    this.eventSubscriber = this.eventManager.subscribe('entityStructureListModification', response => this.loadAll());
  }
}
