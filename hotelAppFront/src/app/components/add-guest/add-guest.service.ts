import { Injectable } from '@angular/core';
import {Guest} from '../../models/guest';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { HTTP_URL } from '../../shared/constants/app.constants';

@Injectable()
export class AddGuestService {

  constructor(private http: HttpClient) { }

  addGuest(guest: Guest) {
    let headers = new HttpHeaders ({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.post(HTTP_URL + 'guest/add', guest,
      {headers: headers, responseType: 'text'});
  }
}
