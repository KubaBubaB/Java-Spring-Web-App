import {Component, OnInit} from '@angular/core';
import {JobService} from "../../Service/job-service";
import {Jobs} from "../../Model/jobs";
import {Job} from "../../Model/job";

@Component({
  selector: 'app-jobs-list',
  templateUrl: './jobs-list.component.html',
  styleUrls: ['./jobs-list.component.css']
})
export class JobsListComponent implements OnInit{
  /**
   * @param service jobs service
   */
  constructor(private service: JobService) {
  }


  jobs: Jobs | undefined;

  ngOnInit(): void {
    this.service.getJobs().subscribe(jobs => this.jobs = jobs);
  }

  onDelete(job: Job): void {
    this.service.deleteJob(job.id).subscribe(() => this.ngOnInit());
  }


}
