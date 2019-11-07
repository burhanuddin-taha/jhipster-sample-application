import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EntityAuthorityDetailComponent } from 'app/entities/entity-authority/entity-authority-detail.component';
import { EntityAuthority } from 'app/shared/model/entity-authority.model';

describe('Component Tests', () => {
  describe('EntityAuthority Management Detail Component', () => {
    let comp: EntityAuthorityDetailComponent;
    let fixture: ComponentFixture<EntityAuthorityDetailComponent>;
    const route = ({ data: of({ entityAuthority: new EntityAuthority(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [EntityAuthorityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EntityAuthorityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EntityAuthorityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.entityAuthority).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
