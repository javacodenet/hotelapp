import {Component, OnInit} from '@angular/core';
import {LoginService} from "./login.service";
import {EventManagerService} from '../../shared/event-manager.service';
import {AppConstants} from '../../constants/app.constants';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credential = {username: '', password: ''};
  loggedIn = false;

  constructor(private loginService: LoginService,
              private eventManager: EventManagerService) {
  }

  onSubmit() {
    this.loginService.sendCredential(this.credential.username, this.credential.password).subscribe(
      (res: any) => {
        console.log(res);
        localStorage.setItem("xAuthToken", res.token);
        this.loggedIn = true;
        // location.reload();
        this.eventManager.broadcast({name: AppConstants.RELOAD_NAV_BAR,
          content: 'Reload navigation bar'});
      },
      error => {
        console.log(error);
      }
    );
  }

  ngOnInit() {
    this.loginService.checkSession().subscribe(
      () => {
        console.log('success1');
        this.loggedIn = true;
        console.log('success');
      },
      () => {
        this.loggedIn = false;
      }
    );
  }
}
