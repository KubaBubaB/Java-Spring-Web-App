import {Component, OnInit} from '@angular/core';
import {personService} from "../../Service/persons-service";
import {Persons} from "../../Model/persons";
import {Person} from "../../Model/person";

@Component({
  selector: 'app-persons-list',
  templateUrl: './persons-list.component.html',
  styleUrls: ['./persons-list.component.css']
})
export class PersonsListComponent implements OnInit{
  /**
   * @param service persons service
   */
  constructor(private service: personService) {
  }


  persons: Persons | undefined;

  ngOnInit(): void {
    this.service.getPersons().subscribe(persons => this.persons = persons);
  }

  onDelete(person: Person): void {
    this.service.deletePerson(person.id).subscribe(() => this.ngOnInit());
  }

}
