export interface IEntityStructure {
  id?: number;
  category?: string;
  capacity?: number;
  belongToId?: number;
}

export class EntityStructure implements IEntityStructure {
  constructor(public id?: number, public category?: string, public capacity?: number, public belongToId?: number) {}
}
