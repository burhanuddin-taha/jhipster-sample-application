import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EntityStructureDetailComponent } from 'app/entities/entity-structure/entity-structure-detail.component';
import { EntityStructure } from 'app/shared/model/entity-structure.model';

describe('Component Tests', () => {
  describe('EntityStructure Management Detail Component', () => {
    let comp: EntityStructureDetailComponent;
    let fixture: ComponentFixture<EntityStructureDetailComponent>;
    const route = ({ data: of({ entityStructure: new EntityStructure(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [EntityStructureDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EntityStructureDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntityStructureDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.entityStructure).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
