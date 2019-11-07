import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { BusinessTypeComponent } from './business-type.component';
import { BusinessTypeDetailComponent } from './business-type-detail.component';
import { BusinessTypeUpdateComponent } from './business-type-update.component';
import { BusinessTypeDeletePopupComponent, BusinessTypeDeleteDialogComponent } from './business-type-delete-dialog.component';
import { businessTypeRoute, businessTypePopupRoute } from './business-type.route';

const ENTITY_STATES = [...businessTypeRoute, ...businessTypePopupRoute];

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    BusinessTypeComponent,
    BusinessTypeDetailComponent,
    BusinessTypeUpdateComponent,
    BusinessTypeDeleteDialogComponent,
    BusinessTypeDeletePopupComponent
  ],
  entryComponents: [BusinessTypeDeleteDialogComponent]
})
export class JhipsterSampleApplicationBusinessTypeModule {}
