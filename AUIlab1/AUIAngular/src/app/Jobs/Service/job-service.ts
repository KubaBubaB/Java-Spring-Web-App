import {Injectable} from "@angular/core";
import { HttpClient } from "@angular/common/http";
import {Observable} from "rxjs";
import {Jobs} from "../Model/jobs";
import {Job} from "../Model/job";
import {JobDetails} from "../Model/job-details";
import {Persons} from "../../Persons/Model/persons";
@Injectable({
    providedIn: 'root'
    })
export class JobService {
    constructor(private http: HttpClient) {

    }

    deleteJob(uuid: string): Observable<any> {
      return this.http.delete(`/work/jobs/` + uuid);
    }

    getJob(uuid: string): Observable<JobDetails> {
      return this.http.get<JobDetails>('/work/jobs/' + uuid);
    }
    getJobs(): Observable<Jobs> {
      return this.http.get<Jobs>('/work/jobs');
    }

  getPersons(uuid: string): Observable<Persons> {
    return this.http.get<Persons>('/work/persons/jobs/' + uuid);
  }
}
