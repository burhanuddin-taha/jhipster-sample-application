import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EntityAuthority } from 'app/shared/model/entity-authority.model';
import { EntityAuthorityService } from './entity-authority.service';
import { EntityAuthorityComponent } from './entity-authority.component';
import { EntityAuthorityDetailComponent } from './entity-authority-detail.component';
import { EntityAuthorityUpdateComponent } from './entity-authority-update.component';
import { EntityAuthorityDeletePopupComponent } from './entity-authority-delete-dialog.component';
import { IEntityAuthority } from 'app/shared/model/entity-authority.model';

@Injectable({ providedIn: 'root' })
export class EntityAuthorityResolve implements Resolve<IEntityAuthority> {
  constructor(private service: EntityAuthorityService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEntityAuthority> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<EntityAuthority>) => response.ok),
        map((entityAuthority: HttpResponse<EntityAuthority>) => entityAuthority.body)
      );
    }
    return of(new EntityAuthority());
  }
}

export const entityAuthorityRoute: Routes = [
  {
    path: '',
    component: EntityAuthorityComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EntityAuthorities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EntityAuthorityDetailComponent,
    resolve: {
      entityAuthority: EntityAuthorityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EntityAuthorities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EntityAuthorityUpdateComponent,
    resolve: {
      entityAuthority: EntityAuthorityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EntityAuthorities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EntityAuthorityUpdateComponent,
    resolve: {
      entityAuthority: EntityAuthorityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EntityAuthorities'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const entityAuthorityPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: EntityAuthorityDeletePopupComponent,
    resolve: {
      entityAuthority: EntityAuthorityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EntityAuthorities'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
