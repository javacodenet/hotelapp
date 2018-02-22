import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient, HttpEvent, HttpHeaders, HttpRequest} from '@angular/common/http';
import {AppConstants} from '../../constants/app.constants';

@Injectable()
export class UploadFileService {

  constructor(private http: HttpClient) { }

  uploadFile(file: File, id:string): Observable<HttpEvent<{}>> {
    let formData: FormData = new FormData();

    formData.append('file', file);

    let headers = new HttpHeaders({
      'x-auth-token': localStorage.getItem('xAuthToken')
    });

    const req = new HttpRequest('POST', AppConstants.HTTP_URL + 'post' + '/' + id, formData, {
      headers: headers,
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }
}
