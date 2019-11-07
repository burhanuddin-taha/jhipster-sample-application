import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EntityAttributesComponent } from 'app/entities/entity-attributes/entity-attributes.component';
import { EntityAttributesService } from 'app/entities/entity-attributes/entity-attributes.service';
import { EntityAttributes } from 'app/shared/model/entity-attributes.model';

describe('Component Tests', () => {
  describe('EntityAttributes Management Component', () => {
    let comp: EntityAttributesComponent;
    let fixture: ComponentFixture<EntityAttributesComponent>;
    let service: EntityAttributesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [EntityAttributesComponent],
        providers: []
      })
        .overrideTemplate(EntityAttributesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityAttributesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntityAttributesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EntityAttributes(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.entityAttributes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
