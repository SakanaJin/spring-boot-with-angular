import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CourseGetDto } from '../constants/types';
import { catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  constructor(private http: HttpClient) {}

  getCourses() {
    return this.http
      .get<CourseGetDto>(`http://localhost:8080/api/courses`)
      .pipe(catchError(this.handleError));
  }

  getCourse(id: number) {
    return this.http
      .get<CourseGetDto>(`http://localhost:8080/api/courses/${id}`)
      .pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    console.error('an error ocurred', error.message); //change later
    return throwError(() => new Error(error.message)); //
  }
}
