import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';
import { InputTextModule } from 'primeng/inputtext';
import { ApiService } from '../service/api.service';
import { Router } from '@angular/router';
import { ToastService } from '../service/toast.service';
import { ProfessorDto } from '../constants/types';

@Component({
  selector: 'app-professor-create',
  standalone: true,
  imports: [
    InputTextModule,
    DividerModule,
    FormsModule,
    ButtonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './professor-create.component.html',
  styleUrl: './professor-create.component.css',
})
export class ProfessorCreateComponent {
  constructor(
    private api: ApiService,
    private router: Router,
    private toaster: ToastService
  ) {}

  professor: ProfessorDto = {
    name: '',
  };

  onSubmit() {
    this.api.postProfessor(this.professor).subscribe((data) => {
      this.toaster.showToast('success', 'Success', 'Professor Created');
      this.router.navigate(['/professors']);
    });
  }

  onCancel() {
    this.router.navigate(['/professors']);
  }
}
