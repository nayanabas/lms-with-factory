import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from '../model/course';
import { CourseList } from '../model/course-list';
import { User } from '../model/user';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  

  private url = "http://localhost:2000";

  
  

  

  constructor(private http: HttpClient,
    private authenticationService: AuthenticationService
    ) { }

  public register(user: User) {
    console.log('user--->',user);
    return this.http.post(this.url+'/api/v1/userlookup/registerNewUser', user);
}
public getAllCourse(token: string):Observable<Course[]> {
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Bearer '+token
    })
  };
  return this.http.get<Course[]>(this.url+'/api/v1/userlookup/getAllCourse', httpOptions);
}
getCourseByName(jwtToken: string, filter: string):Observable<Course[]> {
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Bearer '+jwtToken
    })
  };
  return this.http.get<Course[]>(this.url+'/api/v1/userlookup/getAllCoursebyFilter/'+`${filter}`, httpOptions);
}
addCourse(jwtToken: string, course: Course):Observable<Course> {
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Bearer '+jwtToken
    })
  };
  return this.http.post<Course>(this.url+'/api/v1/userlookup/addCourse',course, httpOptions);
}
updateCourse(jwtToken: string, course: Course):Observable<Response> {
  console.log("update course service",course);
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Bearer '+jwtToken
    })
  };
  return this.http.put<Response>(this.url+'/api/v1/userlookup/updateCourse/',course, httpOptions);
}
deleteCourse(jwtToken: string, id: number):Observable<Response> {
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Bearer '+jwtToken
    })
  };
  return this.http.delete<Response>(this.url+'/api/v1/userlookup/delete/'+`${id}`, httpOptions);
}
}
