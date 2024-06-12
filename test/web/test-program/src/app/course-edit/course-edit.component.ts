import { Component, OnInit } from '@angular/core';
import { ApiService } from '../service/api.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseDto, CourseGetDto, ProfessorGetDto } from '../constants/types';
import { DividerModule } from 'primeng/divider';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { ToastService } from '../service/toast.service';
import { Observable } from 'rxjs';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import { AsyncPipe, NgFor } from '@angular/common';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';

@Component({
  selector: 'app-course-edit',
  standalone: true,
  imports: [
    DividerModule,
    FormsModule,
    ButtonModule,
    ReactiveFormsModule,
    DropdownModule,
    InputTextModule,
    TableModule,
    NgFor,
    ConfirmDialogModule,
    AsyncPipe,
  ],
  templateUrl: './course-edit.component.html',
  styleUrl: './course-edit.component.css',
})
export class CourseEditComponent implements OnInit {
  constructor(
    private api: ApiService,
    private route: ActivatedRoute,
    private toaster: ToastService,
    private router: Router,
    private confimationService: ConfirmationService
  ) {}

  course: CourseGetDto | undefined;
  editedCourse: CourseDto = {
    name: '',
    description: '',
  };

  professors: Observable<ProfessorGetDto[]> | undefined;
  selectedProfessorId: number | undefined;

  ngOnInit() {
    this.api
      .getCourse(Number(this.route.snapshot.paramMap.get('id')))
      .subscribe((data) => {
        this.course = { ...data };
        this.editedCourse.name = data.name;
        this.editedCourse.description = data.description;
      });

    this.professors = this.api.getProfessors();
  }

  confirm(id: number) {
    this.confimationService.confirm({
      message: 'Are you sure you want to remove this professor',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.removeProfessor(id);
      },
    });
  }

  onSubmit() {
    this.api
      .putCourse(
        this.editedCourse,
        Number(this.route.snapshot.paramMap.get('id'))
      )
      .subscribe((data) => {
        this.toaster.showToast('success', 'Success', 'Course Updated');
        this.router.navigate([`/course/${this.course?.id}`]);
      });
  }

  onCancel() {
    this.router.navigate([`/course/${this.course?.id}`]);
  }

  addProfessor() {
    this.api
      .addProfessor(
        this.editedCourse,
        this.course?.id || 0,
        this.selectedProfessorId || 0
      )
      .subscribe((data) => {
        this.toaster.showToast('success', 'Success', 'Professor Added');
        this.api
          .getCourse(this.course?.id || 0)
          .subscribe((data) => (this.course = { ...data }));
      });
  }

  removeProfessor(id: number) {
    this.api.removeProfessor(this.course?.id || 0, id).subscribe((data) => {
      this.toaster.showToast('success', 'Success', 'Professor Removed');
      this.api
        .getCourse(this.course?.id || 0)
        .subscribe((data) => (this.course = { ...data }));
    });
  }
}
