import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EntityAuthorityDeleteDialogComponent } from 'app/entities/entity-authority/entity-authority-delete-dialog.component';
import { EntityAuthorityService } from 'app/entities/entity-authority/entity-authority.service';

describe('Component Tests', () => {
  describe('EntityAuthority Management Delete Component', () => {
    let comp: EntityAuthorityDeleteDialogComponent;
    let fixture: ComponentFixture<EntityAuthorityDeleteDialogComponent>;
    let service: EntityAuthorityService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [EntityAuthorityDeleteDialogComponent]
      })
        .overrideTemplate(EntityAuthorityDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntityAuthorityDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntityAuthorityService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
