import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegistrationPageComponent } from './registration-page/registration-page.component';
import { AuthguardService } from './service/authguard.service';

const routes: Routes = [
  { path: '', component: LoginPageComponent, canActivate: [AuthguardService] },
  { path: 'login', component: LoginPageComponent },
  { path: 'home', component: HomeComponent },
  { path: 'register', component: RegistrationPageComponent },

  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
