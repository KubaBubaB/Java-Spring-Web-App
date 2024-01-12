import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {PersonForm} from "../../../Persons/Model/person-form";
import {personService} from "../../../Persons/Service/persons-service";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-persons-add',
  templateUrl: './persons-add.component.html',
  styleUrls: ['./persons-add.component.css']
})
export class PersonsAddComponent {

  jobsId: string | undefined;

  personForm: PersonForm | undefined;
  constructor(private router: Router, private personSer: personService, private formBuilder: FormBuilder) {
    const navigation = this.router.getCurrentNavigation();

    if (navigation && navigation.extras.state && "jobsId" in navigation.extras.state) {
      this.jobsId = navigation.extras.state["jobsId"];
    } else {

      this.jobsId = "a63676a0-0008-4168-bfeb-bcdeaa6976de"; //waiter
    }
  }

  public addFormPerson = this.formBuilder.group({
    name: '',
    salary: '',
    uuid: ''
  });

  onSubmit() {
    this.personForm = {
      name: this.addFormPerson.value.name as string,
      salary: parseInt(this.addFormPerson.value.salary as string),
      jobsId: this.jobsId as string
    }
    this.personSer.putPerson(this.addFormPerson.value.uuid!, this.personForm!).subscribe(() => this.router.navigate(['/jobs']));
  }
}
