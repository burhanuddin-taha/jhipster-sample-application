export interface IBusinessType {
  id?: number;
  type?: string;
}

export class BusinessType implements IBusinessType {
  constructor(public id?: number, public type?: string) {}
}
