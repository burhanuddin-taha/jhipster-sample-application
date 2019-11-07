import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EntityStructureUpdateComponent } from 'app/entities/entity-structure/entity-structure-update.component';
import { EntityStructureService } from 'app/entities/entity-structure/entity-structure.service';
import { EntityStructure } from 'app/shared/model/entity-structure.model';

describe('Component Tests', () => {
  describe('EntityStructure Management Update Component', () => {
    let comp: EntityStructureUpdateComponent;
    let fixture: ComponentFixture<EntityStructureUpdateComponent>;
    let service: EntityStructureService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [EntityStructureUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EntityStructureUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityStructureUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntityStructureService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EntityStructure(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new EntityStructure();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
