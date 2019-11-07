import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IEntityAttributes, EntityAttributes } from 'app/shared/model/entity-attributes.model';
import { EntityAttributesService } from './entity-attributes.service';
import { IEntities } from 'app/shared/model/entities.model';
import { EntitiesService } from 'app/entities/entities/entities.service';

@Component({
  selector: 'jhi-entity-attributes-update',
  templateUrl: './entity-attributes-update.component.html'
})
export class EntityAttributesUpdateComponent implements OnInit {
  isSaving: boolean;

  entities: IEntities[];

  editForm = this.fb.group({
    id: [],
    categotyName: [],
    entitiesId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected entityAttributesService: EntityAttributesService,
    protected entitiesService: EntitiesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ entityAttributes }) => {
      this.updateForm(entityAttributes);
    });
    this.entitiesService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IEntities[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEntities[]>) => response.body)
      )
      .subscribe((res: IEntities[]) => (this.entities = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(entityAttributes: IEntityAttributes) {
    this.editForm.patchValue({
      id: entityAttributes.id,
      categotyName: entityAttributes.categotyName,
      entitiesId: entityAttributes.entitiesId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const entityAttributes = this.createFromForm();
    if (entityAttributes.id !== undefined) {
      this.subscribeToSaveResponse(this.entityAttributesService.update(entityAttributes));
    } else {
      this.subscribeToSaveResponse(this.entityAttributesService.create(entityAttributes));
    }
  }

  private createFromForm(): IEntityAttributes {
    return {
      ...new EntityAttributes(),
      id: this.editForm.get(['id']).value,
      categotyName: this.editForm.get(['categotyName']).value,
      entitiesId: this.editForm.get(['entitiesId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntityAttributes>>) {
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
