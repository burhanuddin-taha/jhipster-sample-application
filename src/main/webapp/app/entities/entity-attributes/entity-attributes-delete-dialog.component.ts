import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEntityAttributes } from 'app/shared/model/entity-attributes.model';
import { EntityAttributesService } from './entity-attributes.service';

@Component({
  selector: 'jhi-entity-attributes-delete-dialog',
  templateUrl: './entity-attributes-delete-dialog.component.html'
})
export class EntityAttributesDeleteDialogComponent {
  entityAttributes: IEntityAttributes;

  constructor(
    protected entityAttributesService: EntityAttributesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.entityAttributesService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'entityAttributesListModification',
        content: 'Deleted an entityAttributes'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-entity-attributes-delete-popup',
  template: ''
})
export class EntityAttributesDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ entityAttributes }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(EntityAttributesDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.entityAttributes = entityAttributes;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/entity-attributes', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/entity-attributes', { outlets: { popup: null } }]);
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
