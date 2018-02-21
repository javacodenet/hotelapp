import { Component, OnInit } from '@angular/core';
import {Guest} from '../../models/guest';

@Component({
  selector: 'app-add-guest',
  templateUrl: './add-guest.component.html',
  styleUrls: ['./add-guest.component.css']
})
export class AddGuestComponent implements OnInit {
  newGuest: Guest = new Guest();

  constructor() { }

  ngOnInit() {
  }

}
