import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'entities',
        loadChildren: () => import('./entities/entities.module').then(m => m.JhipsterSampleApplicationEntitiesModule)
      },
      {
        path: 'entity-attributes',
        loadChildren: () =>
          import('./entity-attributes/entity-attributes.module').then(m => m.JhipsterSampleApplicationEntityAttributesModule)
      },
      {
        path: 'entity-authority',
        loadChildren: () => import('./entity-authority/entity-authority.module').then(m => m.JhipsterSampleApplicationEntityAuthorityModule)
      },
      {
        path: 'business-type',
        loadChildren: () => import('./business-type/business-type.module').then(m => m.JhipsterSampleApplicationBusinessTypeModule)
      },
      {
        path: 'entity-structure',
        loadChildren: () => import('./entity-structure/entity-structure.module').then(m => m.JhipsterSampleApplicationEntityStructureModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JhipsterSampleApplicationEntityModule {}
