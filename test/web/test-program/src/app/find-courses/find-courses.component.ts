import { Component, OnInit } from '@angular/core';
import { AsyncPipe, NgFor } from '@angular/common';
import { ApiService } from '../service/api.service';
import { Observable } from 'rxjs';
import { UniversityGetDto } from '../constants/types';
import { Router } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';
import { CardModule } from 'primeng/card';

@Component({
  selector: 'app-find-courses',
  standalone: true,
  imports: [CardModule, NgFor, AsyncPipe, ButtonModule, DividerModule],
  templateUrl: './find-courses.component.html',
  styleUrl: './find-courses.component.css',
})
export class FindCoursesComponent implements OnInit {
  constructor(private api: ApiService, private router: Router) {}

  universities: Observable<UniversityGetDto[]> | undefined;

  ngOnInit() {
    this.universities = this.api.getUniversities();
  }

  toUniversity(id: number) {
    this.router.navigate([`/university/${id}`]);
  }

  toUniversityCreate() {
    this.router.navigate([`/university/create`]);
  }
}
