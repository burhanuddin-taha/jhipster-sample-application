import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBusinessType } from 'app/shared/model/business-type.model';
import { BusinessTypeService } from './business-type.service';

@Component({
  selector: 'jhi-business-type-delete-dialog',
  templateUrl: './business-type-delete-dialog.component.html'
})
export class BusinessTypeDeleteDialogComponent {
  businessType: IBusinessType;

  constructor(
    protected businessTypeService: BusinessTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.businessTypeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'businessTypeListModification',
        content: 'Deleted an businessType'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-business-type-delete-popup',
  template: ''
})
export class BusinessTypeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ businessType }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(BusinessTypeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.businessType = businessType;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/business-type', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/business-type', { outlets: { popup: null } }]);
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
