import { IEntityStructure } from 'app/shared/model/entity-structure.model';
import { IEntityAttributes } from 'app/shared/model/entity-attributes.model';

export interface IEntities {
  id?: number;
  company?: string;
  countryCode?: string;
  state?: string;
  city?: string;
  zipCode?: string;
  vatNumber?: string;
  cf?: string;
  sdi?: string;
  ownedBy?: number;
  geolocation?: string;
  originUserId?: number;
  registration?: string;
  code?: string;
  pec?: string;
  numRivendita?: string;
  website?: string;
  fax?: string;
  profile?: string;
  bankName?: string;
  bankIban?: string;
  ranking?: number;
  businessString?: string;
  origin?: string;
  entityStructures?: IEntityStructure[];
  typeId?: number;
  attributes?: IEntityAttributes[];
}

export class Entities implements IEntities {
  constructor(
    public id?: number,
    public company?: string,
    public countryCode?: string,
    public state?: string,
    public city?: string,
    public zipCode?: string,
    public vatNumber?: string,
    public cf?: string,
    public sdi?: string,
    public ownedBy?: number,
    public geolocation?: string,
    public originUserId?: number,
    public registration?: string,
    public code?: string,
    public pec?: string,
    public numRivendita?: string,
    public website?: string,
    public fax?: string,
    public profile?: string,
    public bankName?: string,
    public bankIban?: string,
    public ranking?: number,
    public businessString?: string,
    public origin?: string,
    public entityStructures?: IEntityStructure[],
    public typeId?: number,
    public attributes?: IEntityAttributes[]
  ) {}
}
