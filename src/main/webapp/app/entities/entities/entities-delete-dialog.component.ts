import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEntities } from 'app/shared/model/entities.model';
import { EntitiesService } from './entities.service';

@Component({
  selector: 'jhi-entities-delete-dialog',
  templateUrl: './entities-delete-dialog.component.html'
})
export class EntitiesDeleteDialogComponent {
  entities: IEntities;

  constructor(protected entitiesService: EntitiesService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.entitiesService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'entitiesListModification',
        content: 'Deleted an entities'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-entities-delete-popup',
  template: ''
})
export class EntitiesDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ entities }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(EntitiesDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.entities = entities;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/entities', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/entities', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
