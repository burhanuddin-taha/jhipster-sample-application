export interface IBusinessType {
  id?: number;
  type?: string;
  entitiesId?: number;
}

export class BusinessType implements IBusinessType {
  constructor(public id?: number, public type?: string, public entitiesId?: number) {}
}
