import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CourseGetDto, UniversityGetDto } from '../constants/types';
import { catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  constructor(private http: HttpClient) {}

  baseUrl: string = 'http://localhost:8080';

  getCourses() {
    return this.http
      .get<CourseGetDto>(this.baseUrl + `/api/courses`)
      .pipe(catchError(this.handleError));
  }

  getCourse(id: number) {
    return this.http
      .get<CourseGetDto>(this.baseUrl + `/api/courses/${id}`)
      .pipe(catchError(this.handleError));
  }

  getUniversity(id: number) {
    return this.http
      .get<UniversityGetDto>(this.baseUrl + `/api/universities/${id}`)
      .pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    console.log('an error ocurred', error.message); //change later
    return throwError(() => new Error(error.message)); //
  }
}
