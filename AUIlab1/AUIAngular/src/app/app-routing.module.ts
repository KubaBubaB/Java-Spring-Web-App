import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PersonsListComponent} from "./Persons/View/persons-list/persons-list.component";
import {PersonsViewComponent} from "./Persons/View/persons-view/persons-view.component";
import {PersonsEditComponent} from "./Persons/View/persons-edit/persons-edit.component";
import {JobsListComponent} from "./Jobs/View/jobs-list/jobs-list.component";
import {JobsViewComponent} from "./Jobs/View/jobs-view/jobs-view.component";
import {JobsEditComponent} from "./Jobs/View/jobs-edit/jobs-edit.component";
import {JobsAddComponent} from "./Jobs/View/jobs-add/jobs-add.component";
import {PersonsAddComponent} from "./Jobs/View/persons-add/persons-add.component";

const routes: Routes = [
  {
    component: PersonsListComponent,
    path: "persons"
  },
  {
    component: PersonsViewComponent,
    path: "persons/:uuid"
  }
  ,
  {
    component: PersonsEditComponent,
    path: "persons/:uuid/edit"
  },
  {
    component: PersonsAddComponent,
    path: "jobs/persons/add"
  },
  {
    component: JobsListComponent,
    path: "jobs"
  },
  {
    component: JobsAddComponent,
    path: "jobs/add"
  },
  {
    component: JobsViewComponent,
    path: "jobs/:uuid"
  },
  {
    component: JobsEditComponent,
    path: "jobs/:uuid/edit"
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
