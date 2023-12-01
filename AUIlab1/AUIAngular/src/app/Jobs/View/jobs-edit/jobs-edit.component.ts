import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {JobForm} from "../../Model/job-form";
import {Jobs} from "../../Model/jobs";
import {JobService} from "../../Service/job-service";

@Component({
  selector: 'app-jobs-edit',
  templateUrl: './jobs-edit.component.html',
  styleUrls: ['./jobs-edit.component.css']
})
export class JobsEditComponent implements OnInit{

  uuid: string | undefined;

  /**
   * Single job.
   */
  job: JobForm | undefined;

  /**
   * Single job.
   */
  original: JobForm | undefined;

  /**
   * Available jobs.
   */
  jobs: Jobs | undefined;

  /**
   * @param jobService job service
   * @param jobService job service
   * @param route activated route
   * @param router router
   */
  constructor(
    private jobService: JobService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.jobService.getJob(params['uuid'])
        .subscribe(job => {
          this.uuid = job.id;
          this.job= {
            name: job.name,
            workingHoursPerDay: job.workHoursPerDay
          }
          this.original = {...this.job};
        });
    });
  }

  /**
   * Updates job.
   */
  onSubmit(): void {
    this.jobService.patchJob(this.uuid!, this.job!)
      .subscribe(() => this.router.navigate(['/jobs']));
  }
}
