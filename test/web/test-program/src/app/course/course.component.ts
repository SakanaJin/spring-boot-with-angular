import { Component, OnInit } from '@angular/core';
import { ApiService } from '../service/api.service';
import { CourseGetDto } from '../constants/types';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-course',
  standalone: true,
  imports: [],
  templateUrl: './course.component.html',
  styleUrl: './course.component.css',
})
export class CourseComponent implements OnInit {
  constructor(private api: ApiService, private route: ActivatedRoute) {}

  course: CourseGetDto | undefined;

  ngOnInit() {
    this.api
      .getCourse(Number(this.route.snapshot.paramMap.get('id')))
      .subscribe((data) => (this.course = { ...data }));
  }
}
