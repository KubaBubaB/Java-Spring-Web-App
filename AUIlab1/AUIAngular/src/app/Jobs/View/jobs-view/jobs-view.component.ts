import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {JobDetails} from "../../Model/job-details";
import {JobService} from "../../Service/job-service";
import {Persons} from "../../../Persons/Model/persons";
import {Person} from "../../../Persons/Model/person";
import {personService} from "../../../Persons/Service/persons-service";
import {state} from "@angular/animations";

@Component({
  selector: 'app-jobs-view',
  templateUrl: './jobs-view.component.html',
  styleUrls: ['./jobs-view.component.css']
})
export class JobsViewComponent implements OnInit{

  job: JobDetails | undefined;
  persons: Persons | undefined;
  constructor(private service: JobService, private route: ActivatedRoute, private router: Router, private personSer: personService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.service.getJob(params['uuid'])
        .subscribe(job => this.job = job);
      this.service.getPersons(params['uuid']).subscribe(persons => this.persons = persons);
    });
  }

  onDeletePerson(person: Person) {
    this.personSer.deletePerson(person.id).subscribe(() => {
      this.ngOnInit();
    });
  }

  goToAddPage() {
    this.router.navigate(['jobs/persons/add'], {state: {jobsId: this.job?.id}});
  }
}
