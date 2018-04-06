import {Component, OnDestroy, OnInit} from '@angular/core';
import {LoginService} from '../login/login.service';
import {Router} from '@angular/router';
import {Subscription} from 'rxjs/Rx';
import {EventManagerService} from '../../shared/event-manager.service';
import { RELOAD_NAV_BAR } from '../../shared/constants/app.constants';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit, OnDestroy {

  loggedIn = false;
  subscription: Subscription;
  loading: boolean;

  constructor(private loginService:LoginService,
              private router:Router,
              private eventManager: EventManagerService) { }

  logout() {
    this.loginService.logout();

    this.router.navigate(['login']);
  }

  ngOnInit() {
    this.loading = true;
    this.subscription = this.eventManager.subscribe(RELOAD_NAV_BAR, () => this.checkSession());
    this.checkSession();
  }

  private checkSession() {
    this.loginService.checkSession().subscribe(
      res => {
        this.loggedIn = true;
        this.loading = false;
      },
      error => {
        this.loggedIn = false;
        this.loading = false;
      }
    );
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.subscription);
  }


}
