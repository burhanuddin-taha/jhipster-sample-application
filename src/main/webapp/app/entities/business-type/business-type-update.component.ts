import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IBusinessType, BusinessType } from 'app/shared/model/business-type.model';
import { BusinessTypeService } from './business-type.service';
import { IEntities } from 'app/shared/model/entities.model';
import { EntitiesService } from 'app/entities/entities/entities.service';

@Component({
  selector: 'jhi-business-type-update',
  templateUrl: './business-type-update.component.html'
})
export class BusinessTypeUpdateComponent implements OnInit {
  isSaving: boolean;

  entities: IEntities[];

  editForm = this.fb.group({
    id: [],
    type: [],
    entitiesId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected businessTypeService: BusinessTypeService,
    protected entitiesService: EntitiesService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ businessType }) => {
      this.updateForm(businessType);
    });
    this.entitiesService
      .query({ filter: 'type-is-null' })
      .pipe(
        filter((mayBeOk: HttpResponse<IEntities[]>) => mayBeOk.ok),
        map((response: HttpResponse<IEntities[]>) => response.body)
      )
      .subscribe(
        (res: IEntities[]) => {
          if (!this.editForm.get('entitiesId').value) {
            this.entities = res;
          } else {
            this.entitiesService
              .find(this.editForm.get('entitiesId').value)
              .pipe(
                filter((subResMayBeOk: HttpResponse<IEntities>) => subResMayBeOk.ok),
                map((subResponse: HttpResponse<IEntities>) => subResponse.body)
              )
              .subscribe(
                (subRes: IEntities) => (this.entities = [subRes].concat(res)),
                (subRes: HttpErrorResponse) => this.onError(subRes.message)
              );
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(businessType: IBusinessType) {
    this.editForm.patchValue({
      id: businessType.id,
      type: businessType.type,
      entitiesId: businessType.entitiesId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const businessType = this.createFromForm();
    if (businessType.id !== undefined) {
      this.subscribeToSaveResponse(this.businessTypeService.update(businessType));
    } else {
      this.subscribeToSaveResponse(this.businessTypeService.create(businessType));
    }
  }

  private createFromForm(): IBusinessType {
    return {
      ...new BusinessType(),
      id: this.editForm.get(['id']).value,
      type: this.editForm.get(['type']).value,
      entitiesId: this.editForm.get(['entitiesId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBusinessType>>) {
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
