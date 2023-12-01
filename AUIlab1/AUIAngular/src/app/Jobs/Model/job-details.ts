import {Person} from "../../Persons/Model/person";

export interface JobDetails{
  id: string;
  name: string;
  workHoursPerDay: Number;
  persons: Person[];
}
