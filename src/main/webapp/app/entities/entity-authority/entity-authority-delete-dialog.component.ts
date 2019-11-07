import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEntityAuthority } from 'app/shared/model/entity-authority.model';
import { EntityAuthorityService } from './entity-authority.service';

@Component({
  selector: 'jhi-entity-authority-delete-dialog',
  templateUrl: './entity-authority-delete-dialog.component.html'
})
export class EntityAuthorityDeleteDialogComponent {
  entityAuthority: IEntityAuthority;

  constructor(
    protected entityAuthorityService: EntityAuthorityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.entityAuthorityService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'entityAuthorityListModification',
        content: 'Deleted an entityAuthority'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-entity-authority-delete-popup',
  template: ''
})
export class EntityAuthorityDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ entityAuthority }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(EntityAuthorityDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.entityAuthority = entityAuthority;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/entity-authority', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/entity-authority', { outlets: { popup: null } }]);
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
