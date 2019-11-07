import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEntityAuthority } from 'app/shared/model/entity-authority.model';

type EntityResponseType = HttpResponse<IEntityAuthority>;
type EntityArrayResponseType = HttpResponse<IEntityAuthority[]>;

@Injectable({ providedIn: 'root' })
export class EntityAuthorityService {
  public resourceUrl = SERVER_API_URL + 'api/entity-authorities';

  constructor(protected http: HttpClient) {}

  create(entityAuthority: IEntityAuthority): Observable<EntityResponseType> {
    return this.http.post<IEntityAuthority>(this.resourceUrl, entityAuthority, { observe: 'response' });
  }

  update(entityAuthority: IEntityAuthority): Observable<EntityResponseType> {
    return this.http.put<IEntityAuthority>(this.resourceUrl, entityAuthority, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEntityAuthority>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEntityAuthority[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
