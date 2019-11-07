import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EntitiesUpdateComponent } from 'app/entities/entities/entities-update.component';
import { EntitiesService } from 'app/entities/entities/entities.service';
import { Entities } from 'app/shared/model/entities.model';

describe('Component Tests', () => {
  describe('Entities Management Update Component', () => {
    let comp: EntitiesUpdateComponent;
    let fixture: ComponentFixture<EntitiesUpdateComponent>;
    let service: EntitiesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [EntitiesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EntitiesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntitiesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntitiesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Entities(123);
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
        const entity = new Entities();
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
