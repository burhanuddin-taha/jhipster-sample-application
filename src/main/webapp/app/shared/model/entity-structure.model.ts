export interface IEntityStructure {
  id?: number;
  category?: string;
  capacity?: number;
  entitiesId?: number;
}

export class EntityStructure implements IEntityStructure {
  constructor(public id?: number, public category?: string, public capacity?: number, public entitiesId?: number) {}
}
