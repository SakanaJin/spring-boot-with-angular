import { Component, OnInit } from '@angular/core';
import { ApiService } from '../service/api.service';
import { UniversityGetDto } from '../constants/types';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';
import { NgFor, NgIf } from '@angular/common';
import { CardModule } from 'primeng/card';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';
import { ToastService } from '../service/toast.service';

@Component({
  selector: 'app-university',
  standalone: true,
  imports: [
    ButtonModule,
    DividerModule,
    NgFor,
    NgIf,
    CardModule,
    ConfirmDialogModule,
    RouterLink,
  ],
  templateUrl: './university.component.html',
  styleUrl: './university.component.css',
})
export class UniversityComponent implements OnInit {
  universities: any;
  constructor(
    private api: ApiService,
    private route: ActivatedRoute,
    private confimationService: ConfirmationService,
    private toast: ToastService,
    private router: Router
  ) {}

  university: UniversityGetDto | undefined;

  ngOnInit() {
    this.api
      .getUniversity(Number(this.route.snapshot.paramMap.get('id')))
      .subscribe((data) => (this.university = { ...data }));
  }

  toCourseCreate() {
    this.router.navigate([`/university/${this.university?.id}/course/create`]);
  }

  toCourse(id: number) {
    this.router.navigate([`course/${id}`]);
  }

  toUniversityEdit() {
    this.router.navigate([`/university/${this.university?.id}/edit`]);
  }

  confirm(id: number) {
    this.confimationService.confirm({
      message: 'Are you sure you want to delete this Course?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.api.deleteCourse(id).subscribe((data) => {
          this.toast.showToast('success', 'Success', 'Course Deleted');
          this.api
            .getUniversity(Number(this.route.snapshot.paramMap.get('id')))
            .subscribe((data) => (this.university = { ...data }));
        });
      },
    });
  }
}
