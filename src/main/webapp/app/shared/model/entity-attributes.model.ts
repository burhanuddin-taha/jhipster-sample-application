export interface IEntityAttributes {
  id?: number;
  categotyName?: string;
  entityIdId?: number;
}

export class EntityAttributes implements IEntityAttributes {
  constructor(public id?: number, public categotyName?: string, public entityIdId?: number) {}
}
