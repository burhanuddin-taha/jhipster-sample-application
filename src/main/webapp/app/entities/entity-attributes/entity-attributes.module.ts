import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { EntityAttributesComponent } from './entity-attributes.component';
import { EntityAttributesDetailComponent } from './entity-attributes-detail.component';
import { EntityAttributesUpdateComponent } from './entity-attributes-update.component';
import { EntityAttributesDeletePopupComponent, EntityAttributesDeleteDialogComponent } from './entity-attributes-delete-dialog.component';
import { entityAttributesRoute, entityAttributesPopupRoute } from './entity-attributes.route';

const ENTITY_STATES = [...entityAttributesRoute, ...entityAttributesPopupRoute];

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    EntityAttributesComponent,
    EntityAttributesDetailComponent,
    EntityAttributesUpdateComponent,
    EntityAttributesDeleteDialogComponent,
    EntityAttributesDeletePopupComponent
  ],
  entryComponents: [EntityAttributesDeleteDialogComponent]
})
export class JhipsterSampleApplicationEntityAttributesModule {}
