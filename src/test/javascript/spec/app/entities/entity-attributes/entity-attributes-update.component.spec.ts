import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EntityAttributesUpdateComponent } from 'app/entities/entity-attributes/entity-attributes-update.component';
import { EntityAttributesService } from 'app/entities/entity-attributes/entity-attributes.service';
import { EntityAttributes } from 'app/shared/model/entity-attributes.model';

describe('Component Tests', () => {
  describe('EntityAttributes Management Update Component', () => {
    let comp: EntityAttributesUpdateComponent;
    let fixture: ComponentFixture<EntityAttributesUpdateComponent>;
    let service: EntityAttributesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [EntityAttributesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EntityAttributesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityAttributesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntityAttributesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EntityAttributes(123);
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
        const entity = new EntityAttributes();
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
