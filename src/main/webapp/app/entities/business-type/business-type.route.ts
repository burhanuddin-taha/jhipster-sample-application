import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BusinessType } from 'app/shared/model/business-type.model';
import { BusinessTypeService } from './business-type.service';
import { BusinessTypeComponent } from './business-type.component';
import { BusinessTypeDetailComponent } from './business-type-detail.component';
import { BusinessTypeUpdateComponent } from './business-type-update.component';
import { BusinessTypeDeletePopupComponent } from './business-type-delete-dialog.component';
import { IBusinessType } from 'app/shared/model/business-type.model';

@Injectable({ providedIn: 'root' })
export class BusinessTypeResolve implements Resolve<IBusinessType> {
  constructor(private service: BusinessTypeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBusinessType> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<BusinessType>) => response.ok),
        map((businessType: HttpResponse<BusinessType>) => businessType.body)
      );
    }
    return of(new BusinessType());
  }
}

export const businessTypeRoute: Routes = [
  {
    path: '',
    component: BusinessTypeComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BusinessTypes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BusinessTypeDetailComponent,
    resolve: {
      businessType: BusinessTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BusinessTypes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BusinessTypeUpdateComponent,
    resolve: {
      businessType: BusinessTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BusinessTypes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BusinessTypeUpdateComponent,
    resolve: {
      businessType: BusinessTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BusinessTypes'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const businessTypePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: BusinessTypeDeletePopupComponent,
    resolve: {
      businessType: BusinessTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'BusinessTypes'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
