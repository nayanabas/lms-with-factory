import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { Course } from '../model/course';
import { CourseList } from '../model/course-list';
import { UserData } from '../model/user-data';
import { AuthenticationService } from '../service/authentication.service';
import { UserServiceService } from '../service/user-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public currentUser!: UserData;
  public courses:any[] =[];
  public viewPage: boolean = false;
  addCourseForm!: FormGroup;
  submitted: boolean = false;
  addCourseFlag: boolean = false;
  loading: boolean = false;
  adminButtonFlag: boolean = false;
  updateCourseFlag: boolean = false;
  updateCourseDetails: Course| any;
  updateCourseForm: FormGroup|any;
  

  public httpOptions!: {
    headers: HttpHeaders;
  };
  filterValue: any;
  
 

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService,
        private userservice: UserServiceService,
        private formBuilder: FormBuilder
    ) {
        this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    }
    get f() { return this.addCourseForm.controls; }
    get g() { return this.updateCourseForm.controls; }
  ngOnInit(): void {
    this.addCourseForm = this.formBuilder.group({
      name: ['', Validators.required],
      technology: ['', Validators.required],
      duration: ['', Validators.required],
      description: ['', [Validators.required]]
  });
  this.updateCourseForm = this.formBuilder.group({
    courseId: ['', Validators.required],
    name: ['', Validators.required],
    technology: ['', Validators.required],
    duration: ['', Validators.required],
    description: ['', [Validators.required]]
});
    this.currentUser = this.authenticationService.currentUserValue;
    console.log();
    if(this.currentUser.user.role[0].roleName ==='ADMIN'){
      this.adminButtonFlag = true;

    }
  
  }

    logout() {
        this.authenticationService.logout();
        this.router.navigate(['/login']);
    }
    addCourse() {
      if (this.addCourseForm.invalid) {
        console.log("invalid form  ");
          return;
      }
     this.submitted = true;
      this.userservice.addCourse(this.currentUser.jwtToken,this.addCourseForm.value).subscribe((course:Course)=>{

        console.log('course-->',course);
        
        this.viewCourse();

      });
     
  }
  updateCourse() {
    
   this.submitted = true;
    this.userservice.updateCourse(this.currentUser.jwtToken,this.updateCourseForm.value).subscribe((response:Response)=>{

      console.log('course-->',response);
      
      this.viewCourse();

    });
   
}
  viewCourse() {
    this.userservice.getAllCourse(this.currentUser.jwtToken).subscribe((courselist:Course[])=>{

      
      this.courses = courselist;
      this.addCourseFlag = false; 
      this.updateCourseFlag = false;
      this.viewPage = true;
      console.log('course s-->',courselist);
    });
}
search() {
  console.log("filter->",this.filterValue);
  this.userservice.getCourseByName(this.currentUser.jwtToken,this.filterValue).subscribe((courseList:Course[])=>{

    console.log('course-->',courseList);
    this.courses = courseList;
    this.filterValue = "";
  });
}
deleteCourse(id:number) {
  console.log("filter->",id);
  this.userservice.deleteCourse(this.currentUser.jwtToken,id).subscribe((response:any)=>{

    console.log('course-->',response);
    
    this.viewCourse();

  });
  
  
}
cancel() {
  console.log("cancelled");
  
  
}
displayAddCourse(){
  this.addCourseFlag = true;
  console.log("addCourseFlag",this.addCourseFlag);
}
displayUpdateCourse(course:any){
  this.updateCourseFlag = true;

  this.updateCourseForm.patchValue(course);
  this.updateCourseDetails = course;
  console.log("updateCourseFlag",this.updateCourseFlag);
}

}
