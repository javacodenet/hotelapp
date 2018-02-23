import { Injectable } from '@angular/core';
import {Guest} from '../../models/guest';
import {AppConstants} from '../../constants/app.constants';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class AddGuestService {

  constructor(private http: HttpClient) { }

  addGuest(guest: Guest) {
    let headers = new HttpHeaders ({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.post(AppConstants.HTTP_URL + 'guest/add', guest,
      {headers: headers, responseType: 'text'});
  }
}
