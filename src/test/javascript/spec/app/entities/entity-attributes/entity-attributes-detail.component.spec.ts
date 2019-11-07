import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EntityAttributesDetailComponent } from 'app/entities/entity-attributes/entity-attributes-detail.component';
import { EntityAttributes } from 'app/shared/model/entity-attributes.model';

describe('Component Tests', () => {
  describe('EntityAttributes Management Detail Component', () => {
    let comp: EntityAttributesDetailComponent;
    let fixture: ComponentFixture<EntityAttributesDetailComponent>;
    const route = ({ data: of({ entityAttributes: new EntityAttributes(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [EntityAttributesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EntityAttributesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntityAttributesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.entityAttributes).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
