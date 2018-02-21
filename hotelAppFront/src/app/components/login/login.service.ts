import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AppConstants} from '../../constants/app.constants';

@Injectable()
export class LoginService {

  constructor(private http: HttpClient) {
  }

  sendCredential(username: string, password: string) {
    console.log('http url: ' + AppConstants.HTTP_URL);
    let url = AppConstants.HTTP_URL + "token";
    let encodedCredentials = btoa(username + ":" + password);
    let basicHeader = "Basic " + encodedCredentials;
    let headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': basicHeader
    });

    return this.http.get(url, {headers: headers});
  }

  checkSession() {
    let url = AppConstants.HTTP_URL + "checkSession";

    let headers = new HttpHeaders({
      'x-auth-token': localStorage.getItem('xAuthToken')
    });

    return this.http.get(url, {headers: headers, responseType:'text'});
  }

  logout() {
    let url = AppConstants.HTTP_URL + "user/logout";

    let headers = new HttpHeaders({
      'x-auth-token': localStorage.getItem('xAuthToken')
    });

    return this.http.post(url, '', {headers: headers, responseType:'text'});
  }

}
