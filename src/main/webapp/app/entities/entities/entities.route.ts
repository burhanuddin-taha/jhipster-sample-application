import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Entities } from 'app/shared/model/entities.model';
import { EntitiesService } from './entities.service';
import { EntitiesComponent } from './entities.component';
import { EntitiesDetailComponent } from './entities-detail.component';
import { EntitiesUpdateComponent } from './entities-update.component';
import { EntitiesDeletePopupComponent } from './entities-delete-dialog.component';
import { IEntities } from 'app/shared/model/entities.model';

@Injectable({ providedIn: 'root' })
export class EntitiesResolve implements Resolve<IEntities> {
  constructor(private service: EntitiesService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEntities> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Entities>) => response.ok),
        map((entities: HttpResponse<Entities>) => entities.body)
      );
    }
    return of(new Entities());
  }
}

export const entitiesRoute: Routes = [
  {
    path: '',
    component: EntitiesComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Entities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EntitiesDetailComponent,
    resolve: {
      entities: EntitiesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Entities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EntitiesUpdateComponent,
    resolve: {
      entities: EntitiesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Entities'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EntitiesUpdateComponent,
    resolve: {
      entities: EntitiesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Entities'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const entitiesPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: EntitiesDeletePopupComponent,
    resolve: {
      entities: EntitiesResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Entities'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
