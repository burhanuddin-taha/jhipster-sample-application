import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EntityAttributesDeleteDialogComponent } from 'app/entities/entity-attributes/entity-attributes-delete-dialog.component';
import { EntityAttributesService } from 'app/entities/entity-attributes/entity-attributes.service';

describe('Component Tests', () => {
  describe('EntityAttributes Management Delete Component', () => {
    let comp: EntityAttributesDeleteDialogComponent;
    let fixture: ComponentFixture<EntityAttributesDeleteDialogComponent>;
    let service: EntityAttributesService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [EntityAttributesDeleteDialogComponent]
      })
        .overrideTemplate(EntityAttributesDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntityAttributesDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntityAttributesService);
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
