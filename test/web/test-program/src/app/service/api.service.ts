import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  CourseDto,
  CourseGetDto,
  ProfessorDto,
  ProfessorGetDto,
  Quote,
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

  postCourse(course: CourseDto, universityId: number, professorId: number) {
    return this.http
      .post<CourseGetDto>(
        this.baseUrl +
          `/api/courses/university/${universityId}/professor/${professorId}`,
        course
      )
      .pipe(
        catchError((error) => {
          this.toast.showToast('error', 'Error', 'Error creating Course');
          return this.handleError(error);
        })
      );
  }

  addProfessor(course: CourseDto, courseId: number, professorId: number) {
    return this.http
      .post<CourseGetDto>(
        this.baseUrl + `/api/courses/${courseId}/professor/${professorId}`,
        course
      )
      .pipe(
        catchError((error) => {
          this.toast.showToast('error', 'Error', 'Error adding Professor');
          return this.handleError(error);
        })
      );
  }

  putCourse(course: CourseDto, id: number) {
    return this.http
      .put<CourseGetDto>(this.baseUrl + `/api/courses/${id}`, course)
      .pipe(
        catchError((error) => {
          this.toast.showToast('error', 'Error', 'Error updating Course');
          return this.handleError(error);
        })
      );
  }

  removeProfessor(courseId: number, professorId: number) {
    return this.http
      .delete<CourseGetDto>(
        this.baseUrl + `/api/courses/${courseId}/professor/${professorId}`
      )
      .pipe(
        catchError((error) => {
          this.toast.showToast('error', 'Error', 'Error removing Professor');
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

  getProfessors() {
    return this.http
      .get<ProfessorGetDto[]>(this.baseUrl + `/api/professors`)
      .pipe(
        catchError((error) => {
          this.toast.showToast('error', 'Error', 'Error fetching Professors');
          return this.handleError(error);
        })
      );
  }

  getProfessor(id: number) {
    return this.http
      .get<ProfessorGetDto>(this.baseUrl + `/api/professors/${id}`)
      .pipe(
        catchError((error) => {
          this.toast.showToast('error', 'Error', 'Error fetching Professor');
          return this.handleError(error);
        })
      );
  }

  postProfessor(professor: ProfessorDto) {
    return this.http
      .post<ProfessorGetDto>(this.baseUrl + `/api/professors`, professor)
      .pipe(
        catchError((error) => {
          this.toast.showToast('error', 'Error', 'Error creating Professor');
          return this.handleError(error);
        })
      );
  }

  putProfessor(professor: ProfessorDto, id: number) {
    return this.http
      .put<ProfessorGetDto>(this.baseUrl + `/api/professors/${id}`, professor)
      .pipe(
        catchError((error) => {
          this.toast.showToast('error', 'Error', 'Error updating Professor');
          return this.handleError(error);
        })
      );
  }

  deleteProfessor(id: number) {
    return this.http
      .delete<ProfessorGetDto>(this.baseUrl + `/api/professors/${id}`)
      .pipe(
        catchError((error) => {
          this.toast.showToast('error', 'Error', 'Error deleting professor');
          return this.handleError(error);
        })
      );
  }

  //fetches a random quote from persona via this api: https://www.ultima.rest/docs
  getQuote() {
    return this.http
      .get<Quote>('https://www.ultima.rest/api/quote/game?title=persona')
      .pipe(
        catchError((error) => {
          this.toast.showToast('error', 'Error', 'Error getting quote');
          return this.handleError(error);
        })
      );
  }

  private handleError(error: HttpErrorResponse) {
    console.log('an error ocurred', error.message);
    return throwError(() => new Error(error.message));
  }
}
