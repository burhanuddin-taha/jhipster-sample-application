import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { EntitiesComponent } from './entities.component';
import { EntitiesDetailComponent } from './entities-detail.component';
import { EntitiesUpdateComponent } from './entities-update.component';
import { EntitiesDeletePopupComponent, EntitiesDeleteDialogComponent } from './entities-delete-dialog.component';
import { entitiesRoute, entitiesPopupRoute } from './entities.route';

const ENTITY_STATES = [...entitiesRoute, ...entitiesPopupRoute];

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    EntitiesComponent,
    EntitiesDetailComponent,
    EntitiesUpdateComponent,
    EntitiesDeleteDialogComponent,
    EntitiesDeletePopupComponent
  ],
  entryComponents: [EntitiesDeleteDialogComponent]
})
export class JhipsterSampleApplicationEntitiesModule {}
