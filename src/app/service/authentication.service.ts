import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, empty, Observable } from 'rxjs';
import { User } from '../model/user';
import { map, catchError } from 'rxjs/operators';
import { UserData } from '../model/user-data';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<UserData>;
    public currentUser: Observable<UserData>;
    private userdata1: UserData = new UserData;
    private url = "http://localhost:2000";

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<UserData>(JSON.parse(localStorage.getItem('currentUser')!));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): UserData {
        return this.currentUserSubject.value;
    }
    login(userName: string, userPassword: string) {
      return this.http.post<any>(this.url+'/authenticate', { userName, userPassword })
          .pipe(map((userdata: any) => {
              // login successful if there's a jwt token in the response
              console.log('userdate-------->',userdata)
              if (userdata && userdata.jwtToken) {
                  // store user details and jwt token in local storage to keep user logged in between page refreshes
                  localStorage.setItem('currentUser', JSON.stringify(userdata));
                  this.currentUserSubject.next(userdata);
                  console.log('inside if userdate-------->',userdata)
              }

              return userdata;
          }));
  }
  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(this.userdata1);
}


 
}
