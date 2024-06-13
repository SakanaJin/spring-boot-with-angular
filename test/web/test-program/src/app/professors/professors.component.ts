import { Component, OnInit } from '@angular/core';
import { ApiService } from '../service/api.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ProfessorGetDto } from '../constants/types';
import { AsyncPipe, NgFor } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';
import { CardModule } from 'primeng/card';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';
import { ToastService } from '../service/toast.service';

@Component({
  selector: 'app-professors',
  standalone: true,
  imports: [
    NgFor,
    AsyncPipe,
    ButtonModule,
    DividerModule,
    CardModule,
    ConfirmDialogModule,
  ],
  templateUrl: './professors.component.html',
  styleUrl: './professors.component.css',
})
export class ProfessorsComponent implements OnInit {
  constructor(
    private api: ApiService,
    private router: Router,
    private confirmationService: ConfirmationService,
    private toaster: ToastService
  ) {}

  professors: Observable<ProfessorGetDto[]> | undefined;

  ngOnInit() {
    this.professors = this.api.getProfessors();
  }

  confirm(id: number) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete this Professor?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.api.deleteProfessor(id).subscribe((data) => {
          this.toaster.showToast('success', 'Success', 'Professor Deleted');
          this.professors = this.api.getProfessors();
        });
      },
    });
  }

  toProfessorCreate() {
    this.router.navigate(['/professor/create']);
  }

  toProfessorEdit(id: number) {
    this.router.navigate([`/professor/${id}/edit`]);
  }
}
