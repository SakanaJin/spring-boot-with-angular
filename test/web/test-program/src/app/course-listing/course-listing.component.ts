import { Component, OnInit } from '@angular/core';
import { CardModule } from 'primeng/card';
import { ApiService } from '../service/api.service';
import { CourseGetDto } from '../constants/types';
import { AsyncPipe, NgFor } from '@angular/common';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-course-listing',
  standalone: true,
  imports: [CardModule, NgFor, AsyncPipe],
  templateUrl: './course-listing.component.html',
  styleUrl: './course-listing.component.css',
})
export class CourseListingComponent implements OnInit {
  constructor(private api: ApiService, private router: Router) {}

  courses: Observable<CourseGetDto[]> | undefined;

  ngOnInit() {
    this.courses = this.api.getCourses();
  }

  click() {
    this.router.navigate(['/']);
  }
}
