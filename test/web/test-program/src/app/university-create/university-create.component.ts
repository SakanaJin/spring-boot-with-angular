import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';
import { InputTextModule } from 'primeng/inputtext';
import { ApiService } from '../service/api.service';
import { UniversityDto } from '../constants/types';
import { Router } from '@angular/router';
import { ToastService } from '../service/toast.service';

@Component({
  selector: 'app-university-create',
  standalone: true,
  imports: [
    InputTextModule,
    DividerModule,
    FormsModule,
    ButtonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './university-create.component.html',
  styleUrl: './university-create.component.css',
})
export class UniversityCreateComponent {
  constructor(
    private api: ApiService,
    private router: Router,
    private toast: ToastService
  ) {}

  university: UniversityDto = {
    name: '',
  };

  onSubmit() {
    this.api.postUniversity(this.university).subscribe((data) => {
      this.toast.showToast('success', 'Success', 'University Created');
      this.router.navigate(['/find/universities']);
    });
  }

  onCancel() {
    this.router.navigate(['/find/universities']);
  }
}
