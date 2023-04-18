import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  loginForm!: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string | any =false;
  isLoggedIn:string | any= false;
  logflag: boolean = false;

  
  constructor(private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private authenticationService:AuthenticationService,
    private router: Router
    ) {
      if (this.authenticationService.currentUserValue) { 
        this.router.navigate(['/']);
    }
     }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      userName: ['', Validators.required],
      userPassword: ['', Validators.required]
  });
   // get return url from route parameters or default to '/'
   this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';

  // this.isLoggedIn = sessionStorage.getItem('currentUser');
  // console.log('this.isLoggedIn->',this.isLoggedIn);
  // this.isLoggedIn===null?this.logflag=true:this.logflag=false;
  // console.log('this.isLoggedIn->',this.logflag);

    
  }

  
  // convenience getter for easy access to form fields
  get f() { return this.loginForm.controls; }

  onSubmit() {
      this.submitted = true;

      // stop here if form is invalid
      if (this.loginForm.invalid) {
          return;
      }
      this.authenticationService.login(this.f.userName.value, this.f.userPassword.value)
      .subscribe(
          data => {
            this.router.navigate(['/home']);
          }
          // error => {
          //     this.alertService.error(error);
          //     this.loading = false;
          // }
          );
}


  }


