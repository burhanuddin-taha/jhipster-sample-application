import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EntityAttributes } from 'app/shared/model/entity-attributes.model';
import { EntityAttributesService } from './entity-attributes.service';
import { EntityAttributesComponent } from './entity-attributes.component';
import { EntityAttributesDetailComponent } from './entity-attributes-detail.component';
import { EntityAttributesUpdateComponent } from './entity-attributes-update.component';
import { EntityAttributesDeletePopupComponent } from './entity-attributes-delete-dialog.component';
import { IEntityAttributes } from 'app/shared/model/entity-attributes.model';

@Injectable({ providedIn: 'root' })
export class EntityAttributesResolve implements Resolve<IEntityAttributes> {
  constructor(private service: EntityAttributesService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEntityAttributes> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<EntityAttributes>) => response.ok),
        map((entityAttributes: HttpResponse<EntityAttributes>) => entityAttributes.body)
      );
    }
    return of(new EntityAttributes());
  }
}

export const entityAttributesRoute: Routes = [
  {
    path: '',
    component: EntityAttributesComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EntityAttributes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EntityAttributesDetailComponent,
    resolve: {
      entityAttributes: EntityAttributesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EntityAttributes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EntityAttributesUpdateComponent,
    resolve: {
      entityAttributes: EntityAttributesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EntityAttributes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EntityAttributesUpdateComponent,
    resolve: {
      entityAttributes: EntityAttributesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EntityAttributes'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const entityAttributesPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: EntityAttributesDeletePopupComponent,
    resolve: {
      entityAttributes: EntityAttributesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EntityAttributes'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
