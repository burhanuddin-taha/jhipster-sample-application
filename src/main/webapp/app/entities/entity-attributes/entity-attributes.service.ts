import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEntityAttributes } from 'app/shared/model/entity-attributes.model';

type EntityResponseType = HttpResponse<IEntityAttributes>;
type EntityArrayResponseType = HttpResponse<IEntityAttributes[]>;

@Injectable({ providedIn: 'root' })
export class EntityAttributesService {
  public resourceUrl = SERVER_API_URL + 'api/entity-attributes';

  constructor(protected http: HttpClient) {}

  create(entityAttributes: IEntityAttributes): Observable<EntityResponseType> {
    return this.http.post<IEntityAttributes>(this.resourceUrl, entityAttributes, { observe: 'response' });
  }

  update(entityAttributes: IEntityAttributes): Observable<EntityResponseType> {
    return this.http.put<IEntityAttributes>(this.resourceUrl, entityAttributes, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEntityAttributes>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEntityAttributes[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
