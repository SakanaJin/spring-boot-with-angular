import { Component, OnInit } from '@angular/core';
import { ApiService } from '../service/api.service';
import { CourseGetDto } from '../constants/types';
import { ActivatedRoute, Router } from '@angular/router';
import { NgFor, SlicePipe } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';

@Component({
  selector: 'app-course',
  standalone: true,
  imports: [NgFor, SlicePipe, ButtonModule, DividerModule],
  templateUrl: './course.component.html',
  styleUrl: './course.component.css',
})
export class CourseComponent implements OnInit {
  constructor(
    private api: ApiService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  course: CourseGetDto | undefined;

  ngOnInit() {
    this.api
      .getCourse(Number(this.route.snapshot.paramMap.get('id')))
      .subscribe((data) => (this.course = { ...data }));
  }

  toCourseEdit() {
    this.router.navigate([`/course/${this.course?.id}/edit`]);
  }
}
