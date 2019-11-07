import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { EntityAuthorityComponent } from 'app/entities/entity-authority/entity-authority.component';
import { EntityAuthorityService } from 'app/entities/entity-authority/entity-authority.service';
import { EntityAuthority } from 'app/shared/model/entity-authority.model';

describe('Component Tests', () => {
  describe('EntityAuthority Management Component', () => {
    let comp: EntityAuthorityComponent;
    let fixture: ComponentFixture<EntityAuthorityComponent>;
    let service: EntityAuthorityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [EntityAuthorityComponent],
        providers: []
      })
        .overrideTemplate(EntityAuthorityComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityAuthorityComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EntityAuthorityService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EntityAuthority(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.entityAuthorities[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
