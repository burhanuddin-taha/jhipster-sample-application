import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EntityStructure } from 'app/shared/model/entity-structure.model';
import { EntityStructureService } from './entity-structure.service';
import { EntityStructureComponent } from './entity-structure.component';
import { EntityStructureDetailComponent } from './entity-structure-detail.component';
import { EntityStructureUpdateComponent } from './entity-structure-update.component';
import { EntityStructureDeletePopupComponent } from './entity-structure-delete-dialog.component';
import { IEntityStructure } from 'app/shared/model/entity-structure.model';

@Injectable({ providedIn: 'root' })
export class EntityStructureResolve implements Resolve<IEntityStructure> {
  constructor(private service: EntityStructureService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEntityStructure> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<EntityStructure>) => response.ok),
        map((entityStructure: HttpResponse<EntityStructure>) => entityStructure.body)
      );
    }
    return of(new EntityStructure());
  }
}

export const entityStructureRoute: Routes = [
  {
    path: '',
    component: EntityStructureComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EntityStructures'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EntityStructureDetailComponent,
    resolve: {
      entityStructure: EntityStructureResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EntityStructures'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EntityStructureUpdateComponent,
    resolve: {
      entityStructure: EntityStructureResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EntityStructures'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EntityStructureUpdateComponent,
    resolve: {
      entityStructure: EntityStructureResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EntityStructures'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const entityStructurePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: EntityStructureDeletePopupComponent,
    resolve: {
      entityStructure: EntityStructureResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'EntityStructures'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
