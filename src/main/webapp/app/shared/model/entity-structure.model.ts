export interface IEntityStructure {
  id?: number;
  category?: string;
  capacity?: number;
  entityIdId?: number;
}

export class EntityStructure implements IEntityStructure {
  constructor(public id?: number, public category?: string, public capacity?: number, public entityIdId?: number) {}
}
