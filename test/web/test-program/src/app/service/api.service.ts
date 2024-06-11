import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  CourseGetDto,
  UniversityDto,
  UniversityGetDto,
} from '../constants/types';
import { catchError, throwError } from 'rxjs';
import { ToastService } from './toast.service';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  constructor(private http: HttpClient, private toast: ToastService) {}

  baseUrl: string = 'http://localhost:8080';

  getCourses() {
    return this.http.get<CourseGetDto[]>(this.baseUrl + `/api/courses`).pipe(
      catchError((error) => {
        this.toast.showToast('error', 'Error', 'Error getting Courses');
        return this.handleError(error);
      })
    );
  }

  getCourse(id: number) {
    return this.http
      .get<CourseGetDto>(this.baseUrl + `/api/courses/${id}`)
      .pipe(
        catchError((error) => {
          this.toast.showToast('error', 'Error', 'Error getting Course');
          return this.handleError(error);
        })
      );
  }

  deleteCourse(id: number) {
    return this.http
      .delete<CourseGetDto>(this.baseUrl + `/api/courses/${id}`)
      .pipe(
        catchError((error) => {
          this.toast.showToast('error', 'Error', 'Error deleting Course');
          return this.handleError(error);
        })
      );
  }

  getUniversity(id: number) {
    return this.http
      .get<UniversityGetDto>(this.baseUrl + `/api/universities/${id}`)
      .pipe(
        catchError((error) => {
          this.toast.showToast('error', 'Error', 'Error getting University');
          return this.handleError(error);
        })
      );
  }

  getUniversities() {
    return this.http
      .get<UniversityGetDto[]>(this.baseUrl + `/api/universities`)
      .pipe(
        catchError((error) => {
          this.toast.showToast('error', 'Error', 'Error fetching Universities');
          return this.handleError(error);
        })
      );
  }

  postUniversity(university: UniversityDto) {
    return this.http
      .post<UniversityGetDto>(this.baseUrl + `/api/universities`, university)
      .pipe(
        catchError((error) => {
          this.toast.showToast('error', 'Error', 'Error creating University');
          return this.handleError(error);
        })
      );
  }

  putUniversity(university: UniversityDto, id: number) {
    return this.http
      .put<UniversityGetDto>(
        this.baseUrl + `/api/universities/${id}`,
        university
      )
      .pipe(
        catchError((error) => {
          this.toast.showToast('error', 'Error', 'Error updating University');
          return this.handleError(error);
        })
      );
  }

  deleteUniversity(id: number) {
    return this.http
      .delete<UniversityGetDto>(this.baseUrl + `/api/universities/${id}`)
      .pipe(
        catchError((error) => {
          this.toast.showToast('error', 'Error', 'Error deleting University');
          return this.handleError(error);
        })
      );
  }

  private handleError(error: HttpErrorResponse) {
    console.log('an error ocurred', error.message); //change later
    return throwError(() => new Error(error.message)); //
  }
}
