import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { EntityAuthorityComponent } from './entity-authority.component';
import { EntityAuthorityDetailComponent } from './entity-authority-detail.component';
import { EntityAuthorityUpdateComponent } from './entity-authority-update.component';
import { EntityAuthorityDeletePopupComponent, EntityAuthorityDeleteDialogComponent } from './entity-authority-delete-dialog.component';
import { entityAuthorityRoute, entityAuthorityPopupRoute } from './entity-authority.route';

const ENTITY_STATES = [...entityAuthorityRoute, ...entityAuthorityPopupRoute];

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    EntityAuthorityComponent,
    EntityAuthorityDetailComponent,
    EntityAuthorityUpdateComponent,
    EntityAuthorityDeleteDialogComponent,
    EntityAuthorityDeletePopupComponent
  ],
  entryComponents: [EntityAuthorityDeleteDialogComponent]
})
export class JhipsterSampleApplicationEntityAuthorityModule {}
