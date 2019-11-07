import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EntityAuthorityUpdateComponent } from 'app/entities/entity-authority/entity-authority-update.component';
import { EntityAuthorityService } from 'app/entities/entity-authority/entity-authority.service';
import { EntityAuthority } from 'app/shared/model/entity-authority.model';

describe('Component Tests', () => {
  describe('EntityAuthority Management Update Component', () => {
    let comp: EntityAuthorityUpdateComponent;
    let fixture: ComponentFixture<EntityAuthorityUpdateComponent>;
    let service: EntityAuthorityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [EntityAuthorityUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EntityAuthorityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityAuthorityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntityAuthorityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EntityAuthority(123);
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
        const entity = new EntityAuthority();
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
