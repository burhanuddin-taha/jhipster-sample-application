import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IEntities, Entities } from 'app/shared/model/entities.model';
import { EntitiesService } from './entities.service';
import { IBusinessType } from 'app/shared/model/business-type.model';
import { BusinessTypeService } from 'app/entities/business-type/business-type.service';

@Component({
  selector: 'jhi-entities-update',
  templateUrl: './entities-update.component.html'
})
export class EntitiesUpdateComponent implements OnInit {
  isSaving: boolean;

  businesstypes: IBusinessType[];

  editForm = this.fb.group({
    id: [],
    company: [],
    countryCode: [],
    state: [],
    city: [],
    zipCode: [],
    vatNumber: [],
    cf: [],
    sdi: [],
    ownedBy: [],
    geolocation: [],
    originUserId: [],
    registration: [],
    code: [],
    pec: [],
    numRivendita: [],
    website: [],
    fax: [],
    profile: [],
    bankName: [],
    bankIban: [],
    ranking: [],
    businessString: [],
    origin: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected entitiesService: EntitiesService,
    protected businessTypeService: BusinessTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ entities }) => {
      this.updateForm(entities);
    });
    this.businessTypeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IBusinessType[]>) => mayBeOk.ok),
        map((response: HttpResponse<IBusinessType[]>) => response.body)
      )
      .subscribe((res: IBusinessType[]) => (this.businesstypes = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(entities: IEntities) {
    this.editForm.patchValue({
      id: entities.id,
      company: entities.company,
      countryCode: entities.countryCode,
      state: entities.state,
      city: entities.city,
      zipCode: entities.zipCode,
      vatNumber: entities.vatNumber,
      cf: entities.cf,
      sdi: entities.sdi,
      ownedBy: entities.ownedBy,
      geolocation: entities.geolocation,
      originUserId: entities.originUserId,
      registration: entities.registration,
      code: entities.code,
      pec: entities.pec,
      numRivendita: entities.numRivendita,
      website: entities.website,
      fax: entities.fax,
      profile: entities.profile,
      bankName: entities.bankName,
      bankIban: entities.bankIban,
      ranking: entities.ranking,
      businessString: entities.businessString,
      origin: entities.origin
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const entities = this.createFromForm();
    if (entities.id !== undefined) {
      this.subscribeToSaveResponse(this.entitiesService.update(entities));
    } else {
      this.subscribeToSaveResponse(this.entitiesService.create(entities));
    }
  }

  private createFromForm(): IEntities {
    return {
      ...new Entities(),
      id: this.editForm.get(['id']).value,
      company: this.editForm.get(['company']).value,
      countryCode: this.editForm.get(['countryCode']).value,
      state: this.editForm.get(['state']).value,
      city: this.editForm.get(['city']).value,
      zipCode: this.editForm.get(['zipCode']).value,
      vatNumber: this.editForm.get(['vatNumber']).value,
      cf: this.editForm.get(['cf']).value,
      sdi: this.editForm.get(['sdi']).value,
      ownedBy: this.editForm.get(['ownedBy']).value,
      geolocation: this.editForm.get(['geolocation']).value,
      originUserId: this.editForm.get(['originUserId']).value,
      registration: this.editForm.get(['registration']).value,
      code: this.editForm.get(['code']).value,
      pec: this.editForm.get(['pec']).value,
      numRivendita: this.editForm.get(['numRivendita']).value,
      website: this.editForm.get(['website']).value,
      fax: this.editForm.get(['fax']).value,
      profile: this.editForm.get(['profile']).value,
      bankName: this.editForm.get(['bankName']).value,
      bankIban: this.editForm.get(['bankIban']).value,
      ranking: this.editForm.get(['ranking']).value,
      businessString: this.editForm.get(['businessString']).value,
      origin: this.editForm.get(['origin']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntities>>) {
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

  trackBusinessTypeById(index: number, item: IBusinessType) {
    return item.id;
  }
}
