import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IBusinessType, BusinessType } from 'app/shared/model/business-type.model';
import { BusinessTypeService } from './business-type.service';

@Component({
  selector: 'jhi-business-type-update',
  templateUrl: './business-type-update.component.html'
})
export class BusinessTypeUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    type: []
  });

  constructor(protected businessTypeService: BusinessTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ businessType }) => {
      this.updateForm(businessType);
    });
  }

  updateForm(businessType: IBusinessType) {
    this.editForm.patchValue({
      id: businessType.id,
      type: businessType.type
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
      type: this.editForm.get(['type']).value
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
}
