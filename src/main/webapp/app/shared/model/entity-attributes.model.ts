export interface IEntityAttributes {
  id?: number;
  categotyName?: string;
  idId?: number;
}

export class EntityAttributes implements IEntityAttributes {
  constructor(public id?: number, public categotyName?: string, public idId?: number) {}
}
