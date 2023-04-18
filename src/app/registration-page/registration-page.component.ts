import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../model/user';
import { UserServiceService } from '../service/user-service.service';


@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent implements OnInit {

  registerForm!: FormGroup;
  submitted: boolean = false;
  loading: boolean = false;
  constructor(
    private formBuilder: FormBuilder,
    private userService: UserServiceService,
    private router: Router
  ) { }

  get f() { return this.registerForm.controls; }

  ngOnInit(): void {

    this.registerForm = this.formBuilder.group({
      userFirstName: ['', Validators.required],
      userLastName: ['', Validators.required],
      userName: ['', Validators.required],
      userPassword: ['', [Validators.required, Validators.minLength(6)]]
  });
  }
  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      console.log("invalid form  ");
        return;
    }

    this.loading = false;
    this.userService.register(this.registerForm.value).subscribe(
        data => {
          console.log("registered successfully",data);
            this.router.navigate(['/login']);
        });
        this.router.navigate(['/login']);
    console.log("registered successfully",this.registerForm.value);
  }

}
