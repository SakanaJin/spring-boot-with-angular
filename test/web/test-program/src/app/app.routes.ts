import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { LandingComponent } from './landing/landing.component';
import { CourseListingComponent } from './course-listing/course-listing.component';

export const routes: Routes = [
  { path: '', component: LandingComponent },
  { path: 'courses', component: CourseListingComponent },
];
