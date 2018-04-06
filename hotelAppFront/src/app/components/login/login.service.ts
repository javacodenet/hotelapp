import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { SERVER_API_URL } from '../../shared/constants/app.constants';
import { AuthServerProvider } from '../../shared/auth/auth-jwt.service';
// import { Principal } from '../../shared/auth/principal.service';

@Injectable()
export class LoginService {

  constructor(private http: HttpClient,
              // private principal: Principal,
              private authServerProvider: AuthServerProvider) {
  }

  sendCredential(credentials, callback?) {
    const cb = callback || function() {};

    return new Promise((resolve, reject) => {
      this.authServerProvider.login(credentials).subscribe((data) => {
          resolve(data);
        return cb();
      }, (err) => {
        this.logout();
        reject(err);
        return cb(err);
      });
    });
  }

  checkSession() {
    let url = SERVER_API_URL + "api/checkSession";

    let authToken = '';
    if (localStorage.getItem('xAuthToken')) {
      authToken = localStorage.getItem('xAuthToken');
    }

    let headers = new HttpHeaders({
      'x-auth-token': authToken
    });

    return this.http.get(url, {headers: headers, responseType: 'text'});
  }

  logout() {
    this.authServerProvider.logout().subscribe();
    // this.principal.authenticate(null);
  }

}
