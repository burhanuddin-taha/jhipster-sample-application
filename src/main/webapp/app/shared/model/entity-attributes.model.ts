export interface IEntityAttributes {
  id?: number;
  categotyName?: string;
  entitiesId?: number;
}

export class EntityAttributes implements IEntityAttributes {
  constructor(public id?: number, public categotyName?: string, public entitiesId?: number) {}
}
