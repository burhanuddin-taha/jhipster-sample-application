import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEntityStructure } from 'app/shared/model/entity-structure.model';

type EntityResponseType = HttpResponse<IEntityStructure>;
type EntityArrayResponseType = HttpResponse<IEntityStructure[]>;

@Injectable({ providedIn: 'root' })
export class EntityStructureService {
  public resourceUrl = SERVER_API_URL + 'api/entity-structures';

  constructor(protected http: HttpClient) {}

  create(entityStructure: IEntityStructure): Observable<EntityResponseType> {
    return this.http.post<IEntityStructure>(this.resourceUrl, entityStructure, { observe: 'response' });
  }

  update(entityStructure: IEntityStructure): Observable<EntityResponseType> {
    return this.http.put<IEntityStructure>(this.resourceUrl, entityStructure, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEntityStructure>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEntityStructure[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
