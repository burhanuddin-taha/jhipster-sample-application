import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IEntityAuthority, EntityAuthority } from 'app/shared/model/entity-authority.model';
import { EntityAuthorityService } from './entity-authority.service';

@Component({
  selector: 'jhi-entity-authority-update',
  templateUrl: './entity-authority-update.component.html'
})
export class EntityAuthorityUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    userId: [],
    entityId: [],
    entityName: []
  });

  constructor(
    protected entityAuthorityService: EntityAuthorityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ entityAuthority }) => {
      this.updateForm(entityAuthority);
    });
  }

  updateForm(entityAuthority: IEntityAuthority) {
    this.editForm.patchValue({
      id: entityAuthority.id,
      userId: entityAuthority.userId,
      entityId: entityAuthority.entityId,
      entityName: entityAuthority.entityName
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const entityAuthority = this.createFromForm();
    if (entityAuthority.id !== undefined) {
      this.subscribeToSaveResponse(this.entityAuthorityService.update(entityAuthority));
    } else {
      this.subscribeToSaveResponse(this.entityAuthorityService.create(entityAuthority));
    }
  }

  private createFromForm(): IEntityAuthority {
    return {
      ...new EntityAuthority(),
      id: this.editForm.get(['id']).value,
      userId: this.editForm.get(['userId']).value,
      entityId: this.editForm.get(['entityId']).value,
      entityName: this.editForm.get(['entityName']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntityAuthority>>) {
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
