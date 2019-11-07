import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEntities } from 'app/shared/model/entities.model';

type EntityResponseType = HttpResponse<IEntities>;
type EntityArrayResponseType = HttpResponse<IEntities[]>;

@Injectable({ providedIn: 'root' })
export class EntitiesService {
  public resourceUrl = SERVER_API_URL + 'api/entities';

  constructor(protected http: HttpClient) {}

  create(entities: IEntities): Observable<EntityResponseType> {
    return this.http.post<IEntities>(this.resourceUrl, entities, { observe: 'response' });
  }

  update(entities: IEntities): Observable<EntityResponseType> {
    return this.http.put<IEntities>(this.resourceUrl, entities, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEntities>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEntities[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
