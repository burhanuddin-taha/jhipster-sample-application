import { IEntityAttributes } from 'app/shared/model/entity-attributes.model';
import { IEntityStructure } from 'app/shared/model/entity-structure.model';

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
  business?: string;
  origin?: string;
  attributes?: IEntityAttributes[];
  structures?: IEntityStructure[];
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
    public business?: string,
    public origin?: string,
    public attributes?: IEntityAttributes[],
    public structures?: IEntityStructure[]
  ) {}
}
