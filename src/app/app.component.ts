import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserData } from './model/user-data';
import { AuthenticationService } from './service/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  // public currentUser!: UserData;

  //   constructor(
  //       private router: Router,
  //       private authenticationService: AuthenticationService
  //   ) {
  //       this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  //   }

  //   logout() {
  //       this.authenticationService.logout();
  //       this.router.navigate(['/login']);
  //   }
}
