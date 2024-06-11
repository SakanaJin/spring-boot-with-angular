import { Component, OnInit } from '@angular/core';
import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { ApiService } from '../service/api.service';
import { Observable } from 'rxjs';
import { UniversityGetDto } from '../constants/types';
import { Router } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';
import { CardModule } from 'primeng/card';
import { ConfirmationService } from 'primeng/api';
import { ToastService } from '../service/toast.service';
import { ConfirmDialogModule } from 'primeng/confirmdialog';

@Component({
  selector: 'app-find-courses',
  standalone: true,
  imports: [
    CardModule,
    NgFor,
    NgIf,
    AsyncPipe,
    ButtonModule,
    DividerModule,
    ConfirmDialogModule,
  ],
  templateUrl: './find-courses.component.html',
  styleUrl: './find-courses.component.css',
})
export class FindCoursesComponent implements OnInit {
  constructor(
    private api: ApiService,
    private router: Router,
    private confimationService: ConfirmationService,
    private toast: ToastService
  ) {}

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

  confirm(id: number) {
    this.confimationService.confirm({
      message: 'Are you sure you want to delete this University?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.api.deleteUniversity(id).subscribe((data) => {
          this.toast.showToast('success', 'Success', 'University Deleted');
          this.universities = this.api.getUniversities();
        });
      },
    });
  }
}
