import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Persons} from "../Model/persons";
import {PersonForm} from "../Model/person-form";
import {PersonDetails} from "../Model/person-details";

@Injectable()
export class personService {

  constructor(private http: HttpClient) {

  }

  getPersons(): Observable<Persons> {
    return this.http.get<Persons>('/work/persons');
  }

  getPerson(uuid: string): Observable<PersonDetails> {
    return this.http.get<PersonDetails>('/work/persons/' + uuid);
  }

  deletePerson(uuid: string): Observable<any> {
    return this.http.delete('/work/persons/' + uuid);
  }

  putPerson(uuid: string, request: PersonForm): Observable<any> {
    return this.http.put('/work/persons/' + uuid, request);
  }

}
