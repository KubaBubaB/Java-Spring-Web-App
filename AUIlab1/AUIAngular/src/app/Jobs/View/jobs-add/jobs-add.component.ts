import {Component, OnInit} from '@angular/core';
import {JobService} from "../../Service/job-service";
import {ActivatedRoute, Router} from "@angular/router";
import {JobFormAdd} from "../../Model/job-form-add";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-jobs-add',
  templateUrl: './jobs-add.component.html',
  styleUrls: ['./jobs-add.component.css']
})
export class JobsAddComponent{

  job: JobFormAdd | undefined;
  uuid: string | number | null | undefined;
  constructor(
    private jobService: JobService,
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder
  ) {}

  public addForm = this.formBuilder.group({
    uuid: '',
    name: '',
    workingHours: ''
  });

  /**
   * Creates job.
   */

  onSubmit() {
    console.log("submit job request");
    this.uuid = this.addForm.value.uuid as string;
    this.job = {
      name: this.addForm.value.name as string,
      workingHours: parseInt(this.addForm.value.workingHours as string)
    }
    this.jobService.putJob(this.uuid!, this.job!).subscribe(() => this.router.navigate(['/jobs']));
  }
}
