export interface IEntityAuthority {
  id?: number;
  userId?: number;
  entityId?: number;
  entityName?: string;
}

export class EntityAuthority implements IEntityAuthority {
  constructor(public id?: number, public userId?: number, public entityId?: number, public entityName?: string) {}
}
