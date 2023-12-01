import {Component, OnInit} from '@angular/core';
import {PersonForm} from "../../Model/person-form";
import {ActivatedRoute, Router} from "@angular/router";
import {personService} from "../../Service/persons-service";
import {JobService} from "../../../Jobs/Service/job-service";
import {Jobs} from "../../../Jobs/Model/jobs";

@Component({
  selector: 'app-persons-edit',
  templateUrl: './persons-edit.component.html',
  styleUrls: ['./persons-edit.component.css']
})
export class PersonsEditComponent implements OnInit{
  /**
   * person's id.
   */
  uuid: string | undefined;

  /**
   * Single person.
   */
  person: PersonForm | undefined;

  /**
   * Single person.
   */
  original: PersonForm | undefined;

  /**
   * Available jobs.
   */
  jobs: Jobs | undefined;

  /**
   * @param personService person service
   * @param jobService job service
   * @param route activated route
   * @param router router
   */
  constructor(
    private personService: personService,
    private jobService: JobService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.jobService.getJobs()
        .subscribe(jobs => this.jobs = jobs);

      this.personService.getPerson(params['uuid'])
        .subscribe(person => {
          this.uuid = person.id;
          this.person = {
            name: person.name,
            salary: person.salary,
            jobsId: person.job.id
          };
          this.original = {...this.person};
        });
    });
  }

  /**
   * Updates person.
   */
  onSubmit(): void {
    this.personService.putPerson(this.uuid!, this.person!)
      .subscribe(() => this.router.navigate(['/persons']));
  }

}
