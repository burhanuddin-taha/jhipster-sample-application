import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { EntityStructureComponent } from './entity-structure.component';
import { EntityStructureDetailComponent } from './entity-structure-detail.component';
import { EntityStructureUpdateComponent } from './entity-structure-update.component';
import { EntityStructureDeletePopupComponent, EntityStructureDeleteDialogComponent } from './entity-structure-delete-dialog.component';
import { entityStructureRoute, entityStructurePopupRoute } from './entity-structure.route';

const ENTITY_STATES = [...entityStructureRoute, ...entityStructurePopupRoute];

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    EntityStructureComponent,
    EntityStructureDetailComponent,
    EntityStructureUpdateComponent,
    EntityStructureDeleteDialogComponent,
    EntityStructureDeletePopupComponent
  ],
  entryComponents: [EntityStructureDeleteDialogComponent]
})
export class JhipsterSampleApplicationEntityStructureModule {}
