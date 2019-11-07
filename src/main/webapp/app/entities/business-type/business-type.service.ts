import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBusinessType } from 'app/shared/model/business-type.model';

type EntityResponseType = HttpResponse<IBusinessType>;
type EntityArrayResponseType = HttpResponse<IBusinessType[]>;

@Injectable({ providedIn: 'root' })
export class BusinessTypeService {
  public resourceUrl = SERVER_API_URL + 'api/business-types';

  constructor(protected http: HttpClient) {}

  create(businessType: IBusinessType): Observable<EntityResponseType> {
    return this.http.post<IBusinessType>(this.resourceUrl, businessType, { observe: 'response' });
  }

  update(businessType: IBusinessType): Observable<EntityResponseType> {
    return this.http.put<IBusinessType>(this.resourceUrl, businessType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBusinessType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBusinessType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
