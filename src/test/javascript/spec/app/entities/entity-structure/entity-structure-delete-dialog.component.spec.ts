import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EntityStructureDeleteDialogComponent } from 'app/entities/entity-structure/entity-structure-delete-dialog.component';
import { EntityStructureService } from 'app/entities/entity-structure/entity-structure.service';

describe('Component Tests', () => {
  describe('EntityStructure Management Delete Component', () => {
    let comp: EntityStructureDeleteDialogComponent;
    let fixture: ComponentFixture<EntityStructureDeleteDialogComponent>;
    let service: EntityStructureService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [EntityStructureDeleteDialogComponent]
      })
        .overrideTemplate(EntityStructureDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntityStructureDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntityStructureService);
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
