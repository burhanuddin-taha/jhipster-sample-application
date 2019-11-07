import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IEntityStructure, EntityStructure } from 'app/shared/model/entity-structure.model';
import { EntityStructureService } from './entity-structure.service';
import { IEntities } from 'app/shared/model/entities.model';
import { EntitiesService } from 'app/entities/entities/entities.service';

@Component({
  selector: 'jhi-entity-structure-update',
  templateUrl: './entity-structure-update.component.html'
})
export class EntityStructureUpdateComponent implements OnInit {
  isSaving: boolean;

  entities: IEntities[];

  editForm = this.fb.group({
    id: [],
    category: [],
    capacity: [],
    entitiesId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected entityStructureService: EntityStructureService,
    protected entitiesService: EntitiesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ entityStructure }) => {
      this.updateForm(entityStructure);
    });
    this.entitiesService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IEntities[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEntities[]>) => response.body)
      )
      .subscribe((res: IEntities[]) => (this.entities = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(entityStructure: IEntityStructure) {
    this.editForm.patchValue({
      id: entityStructure.id,
      category: entityStructure.category,
      capacity: entityStructure.capacity,
      entitiesId: entityStructure.entitiesId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const entityStructure = this.createFromForm();
    if (entityStructure.id !== undefined) {
      this.subscribeToSaveResponse(this.entityStructureService.update(entityStructure));
    } else {
      this.subscribeToSaveResponse(this.entityStructureService.create(entityStructure));
    }
  }

  private createFromForm(): IEntityStructure {
    return {
      ...new EntityStructure(),
      id: this.editForm.get(['id']).value,
      category: this.editForm.get(['category']).value,
      capacity: this.editForm.get(['capacity']).value,
      entitiesId: this.editForm.get(['entitiesId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntityStructure>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackEntitiesById(index: number, item: IEntities) {
    return item.id;
  }
}
