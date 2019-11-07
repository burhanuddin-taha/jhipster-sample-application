import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { EntitiesService } from 'app/entities/entities/entities.service';
import { IEntities, Entities } from 'app/shared/model/entities.model';

describe('Service Tests', () => {
  describe('Entities Service', () => {
    let injector: TestBed;
    let service: EntitiesService;
    let httpMock: HttpTestingController;
    let elemDefault: IEntities;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(EntitiesService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Entities(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Entities', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new Entities(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Entities', () => {
        const returnedFromService = Object.assign(
          {
            company: 'BBBBBB',
            countryCode: 'BBBBBB',
            state: 'BBBBBB',
            city: 'BBBBBB',
            zipCode: 'BBBBBB',
            vatNumber: 'BBBBBB',
            cf: 'BBBBBB',
            sdi: 'BBBBBB',
            ownedBy: 1,
            geolocation: 'BBBBBB',
            originUserId: 1,
            registration: 'BBBBBB',
            code: 'BBBBBB',
            pec: 'BBBBBB',
            numRivendita: 'BBBBBB',
            website: 'BBBBBB',
            fax: 'BBBBBB',
            profile: 'BBBBBB',
            bankName: 'BBBBBB',
            bankIban: 'BBBBBB',
            ranking: 1,
            business: 'BBBBBB',
            origin: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Entities', () => {
        const returnedFromService = Object.assign(
          {
            company: 'BBBBBB',
            countryCode: 'BBBBBB',
            state: 'BBBBBB',
            city: 'BBBBBB',
            zipCode: 'BBBBBB',
            vatNumber: 'BBBBBB',
            cf: 'BBBBBB',
            sdi: 'BBBBBB',
            ownedBy: 1,
            geolocation: 'BBBBBB',
            originUserId: 1,
            registration: 'BBBBBB',
            code: 'BBBBBB',
            pec: 'BBBBBB',
            numRivendita: 'BBBBBB',
            website: 'BBBBBB',
            fax: 'BBBBBB',
            profile: 'BBBBBB',
            bankName: 'BBBBBB',
            bankIban: 'BBBBBB',
            ranking: 1,
            business: 'BBBBBB',
            origin: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Entities', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
