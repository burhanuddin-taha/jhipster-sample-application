import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { BusinessTypeDetailComponent } from 'app/entities/business-type/business-type-detail.component';
import { BusinessType } from 'app/shared/model/business-type.model';

describe('Component Tests', () => {
  describe('BusinessType Management Detail Component', () => {
    let comp: BusinessTypeDetailComponent;
    let fixture: ComponentFixture<BusinessTypeDetailComponent>;
    const route = ({ data: of({ businessType: new BusinessType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [BusinessTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BusinessTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BusinessTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.businessType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
