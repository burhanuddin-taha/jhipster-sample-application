import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EntitiesDetailComponent } from 'app/entities/entities/entities-detail.component';
import { Entities } from 'app/shared/model/entities.model';

describe('Component Tests', () => {
  describe('Entities Management Detail Component', () => {
    let comp: EntitiesDetailComponent;
    let fixture: ComponentFixture<EntitiesDetailComponent>;
    const route = ({ data: of({ entities: new Entities(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [EntitiesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EntitiesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntitiesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.entities).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
