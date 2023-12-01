import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HeaderComponent} from "./component/header/header.component";
import {MainComponent} from "./component/main/main.component";
import {NavComponent} from "./component/nav/nav.component";
import {FooterComponent} from "./component/footer/footer.component";
import { PersonsViewComponent } from './Persons/View/persons-view/persons-view.component';
import { PersonsListComponent } from './Persons/View/persons-list/persons-list.component';
import { PersonsEditComponent } from './Persons/View/persons-edit/persons-edit.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { JobsViewComponent } from './Jobs/View/jobs-view/jobs-view.component';
import { JobsListComponent } from './Jobs/View/jobs-list/jobs-list.component';
import { JobsEditComponent } from './Jobs/View/jobs-edit/jobs-edit.component';
import {personService} from "./Persons/Service/persons-service";
import {JobService} from "./Jobs/Service/job-service";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MainComponent,
    NavComponent,
    FooterComponent,
    PersonsViewComponent,
    PersonsListComponent,
    PersonsEditComponent,
    JobsViewComponent,
    JobsListComponent,
    JobsEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [personService,
  JobService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
