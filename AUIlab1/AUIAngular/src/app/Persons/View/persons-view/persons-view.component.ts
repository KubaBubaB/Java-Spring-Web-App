import {Component, OnInit} from '@angular/core';
import { personService } from "../../Service/persons-service";
import { ActivatedRoute, Router } from "@angular/router";
import {PersonDetails} from "../../Model/person-details";

@Component({
  selector: 'app-persons-view',
  templateUrl: './persons-view.component.html',
  styleUrls: ['./persons-view.component.css']
})
export class PersonsViewComponent implements OnInit {
  person: PersonDetails | undefined;

  constructor(private service: personService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.service.getPerson(params['uuid'])
        .subscribe(person => this.person = person)
    });
  }

}
