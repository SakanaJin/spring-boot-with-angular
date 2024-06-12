import { Component, OnInit } from '@angular/core';
import { ApiService } from '../service/api.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastService } from '../service/toast.service';
import { CourseDto, ProfessorGetDto } from '../constants/types';
import { InputTextModule } from 'primeng/inputtext';
import { DividerModule } from 'primeng/divider';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { Observable } from 'rxjs';
import { AsyncPipe, NgIf } from '@angular/common';
import { DropdownModule } from 'primeng/dropdown';

@Component({
  selector: 'app-course-create',
  standalone: true,
  imports: [
    InputTextModule,
    DividerModule,
    FormsModule,
    ButtonModule,
    ReactiveFormsModule,
    AsyncPipe,
    DropdownModule,
    NgIf,
  ],
  templateUrl: './course-create.component.html',
  styleUrl: './course-create.component.css',
})
export class CourseCreateComponent implements OnInit {
  constructor(
    private api: ApiService,
    private router: Router,
    private toaster: ToastService,
    private route: ActivatedRoute
  ) {}

  professors: Observable<ProfessorGetDto[]> | undefined;
  selectedProfessorId: number | undefined;

  universityId: number = Number(
    this.route.snapshot.paramMap.get('universityId')
  );

  course: CourseDto = {
    name: '',
    description: '',
  };

  ngOnInit() {
    this.professors = this.api.getProfessors();
  }

  onSubmit() {
    this.api
      .postCourse(this.course, this.universityId, this.selectedProfessorId || 0)
      .subscribe((data) => {
        // fix this later after multi select is made
        this.toaster.showToast('success', 'Success', 'Course Created');
        this.router.navigate([`/university/${this.universityId}`]);
      });
  }

  onCancel() {
    this.router.navigate([`/university/${this.universityId}`]);
  }
}
