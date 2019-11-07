import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EntitiesComponent } from 'app/entities/entities/entities.component';
import { EntitiesService } from 'app/entities/entities/entities.service';
import { Entities } from 'app/shared/model/entities.model';

describe('Component Tests', () => {
  describe('Entities Management Component', () => {
    let comp: EntitiesComponent;
    let fixture: ComponentFixture<EntitiesComponent>;
    let service: EntitiesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [EntitiesComponent],
        providers: []
      })
        .overrideTemplate(EntitiesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntitiesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntitiesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Entities(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.entities[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
