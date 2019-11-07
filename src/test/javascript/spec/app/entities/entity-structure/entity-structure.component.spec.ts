import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EntityStructureComponent } from 'app/entities/entity-structure/entity-structure.component';
import { EntityStructureService } from 'app/entities/entity-structure/entity-structure.service';
import { EntityStructure } from 'app/shared/model/entity-structure.model';

describe('Component Tests', () => {
  describe('EntityStructure Management Component', () => {
    let comp: EntityStructureComponent;
    let fixture: ComponentFixture<EntityStructureComponent>;
    let service: EntityStructureService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [EntityStructureComponent],
        providers: []
      })
        .overrideTemplate(EntityStructureComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityStructureComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntityStructureService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EntityStructure(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.entityStructures[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
