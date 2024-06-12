import { Routes } from '@angular/router';
import { LandingComponent } from './landing/landing.component';
import { CourseListingComponent } from './course-listing/course-listing.component';
import { CourseComponent } from './course/course.component';
import { CourseCreateComponent } from './course-create/course-create.component';
import { FindCoursesComponent } from './find-courses/find-courses.component';
import { UniversityComponent } from './university/university.component';
import { UniversityCreateComponent } from './university-create/university-create.component';
import { CourseEditComponent } from './course-edit/course-edit.component';

export const routes: Routes = [
  { path: '', component: LandingComponent },
  { path: 'courses', component: CourseListingComponent },
  { path: 'course/:id', component: CourseComponent },
  { path: 'course/:id/edit', component: CourseEditComponent },
  { path: 'courses/create', component: CourseCreateComponent },
  { path: 'find/universities', component: FindCoursesComponent },
  { path: 'university/create', component: UniversityCreateComponent },
  { path: 'university/:id', component: UniversityComponent },
  {
    path: 'university/:universityId/course/create',
    component: CourseCreateComponent,
  },
];
