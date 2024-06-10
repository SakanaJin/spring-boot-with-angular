import { Component, OnInit } from '@angular/core';
import { CardModule } from 'primeng/card';
import { ApiService } from '../service/api.service';
import { CourseGetDto } from '../constants/types';
import { AsyncPipe, NgFor } from '@angular/common';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';

@Component({
  selector: 'app-course-listing',
  standalone: true,
  imports: [CardModule, NgFor, AsyncPipe, ButtonModule, DividerModule],
  templateUrl: './course-listing.component.html',
  styleUrl: './course-listing.component.css',
})
export class CourseListingComponent implements OnInit {
  constructor(private api: ApiService, private router: Router) {}

  courses: Observable<CourseGetDto[]> | undefined;

  ngOnInit() {
    this.courses = this.api.getCourses();
  }

  toCourse(id: number) {
    this.router.navigate([`/course/${id}`]);
  }

  toCourseCreate() {
    this.router.navigate([`/courses/create`]);
  }
}
