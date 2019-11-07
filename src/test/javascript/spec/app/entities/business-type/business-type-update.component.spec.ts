import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BusinessTypeUpdateComponent } from 'app/entities/business-type/business-type-update.component';
import { BusinessTypeService } from 'app/entities/business-type/business-type.service';
import { BusinessType } from 'app/shared/model/business-type.model';

describe('Component Tests', () => {
  describe('BusinessType Management Update Component', () => {
    let comp: BusinessTypeUpdateComponent;
    let fixture: ComponentFixture<BusinessTypeUpdateComponent>;
    let service: BusinessTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BusinessTypeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BusinessTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BusinessTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BusinessTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BusinessType(123);
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
        const entity = new BusinessType();
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
