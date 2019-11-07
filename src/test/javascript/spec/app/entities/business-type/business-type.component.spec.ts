import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BusinessTypeComponent } from 'app/entities/business-type/business-type.component';
import { BusinessTypeService } from 'app/entities/business-type/business-type.service';
import { BusinessType } from 'app/shared/model/business-type.model';

describe('Component Tests', () => {
  describe('BusinessType Management Component', () => {
    let comp: BusinessTypeComponent;
    let fixture: ComponentFixture<BusinessTypeComponent>;
    let service: BusinessTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BusinessTypeComponent],
        providers: []
      })
        .overrideTemplate(BusinessTypeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BusinessTypeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BusinessTypeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BusinessType(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.businessTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
