import {Component, OnDestroy, OnInit} from '@angular/core';
import {LoginService} from '../login/login.service';
import {Router} from '@angular/router';
import {Subscription} from 'rxjs/Rx';
import {EventManagerService} from '../../shared/event-manager.service';
import {AppConstants} from '../../constants/app.constants';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit, OnDestroy {

  loggedIn = false;
  subscription: Subscription;

  constructor(private loginService:LoginService,
              private router:Router,
              private eventManager: EventManagerService) { }

  logout() {
    this.loginService.logout().subscribe(
      res => {
        location.reload();
      },
      error => {
        console.log(error);
      }
    );

    this.router.navigate(['/']);
  }

  ngOnInit() {
    this.subscription = this.eventManager.subscribe(AppConstants.RELOAD_NAV_BAR, () => this.checkSession());
    this.checkSession();
  }

  private checkSession() {
    this.loginService.checkSession().subscribe(
      res => {
        this.loggedIn = true;
      },
      error => {
        this.loggedIn = false;
      }
    );
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.subscription);
  }


}
