import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { CourseGetDto, UniversityGetDto } from '../constants/types';
import { ApiService } from '../service/api.service';
import { NgFor, NgIf } from '@angular/common';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink, RouterOutlet, NgFor],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent {
  constructor(private api: ApiService) {}

  course: CourseGetDto | undefined;
  university: UniversityGetDto | undefined;

  acquire() {
    this.api.getCourse(1).subscribe((data) => (this.course = { ...data }));
    this.api
      .getUniversity(1)
      .subscribe((data) => (this.university = { ...data }));
  }
}
