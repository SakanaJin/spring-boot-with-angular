import { Component } from '@angular/core';
import { FormsModule, NgModel, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';
import { InputTextModule } from 'primeng/inputtext';
import { ApiService } from '../service/api.service';
import { UniversityDto } from '../constants/types';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';

@Component({
  selector: 'app-university-create',
  standalone: true,
  imports: [
    InputTextModule,
    DividerModule,
    FormsModule,
    ButtonModule,
    ReactiveFormsModule,
    ToastModule,
  ],
  templateUrl: './university-create.component.html',
  styleUrl: './university-create.component.css',
  providers: [MessageService],
})
export class UniversityCreateComponent {
  constructor(
    private api: ApiService,
    private router: Router,
    private messageService: MessageService
  ) {}

  name: string = NgModel.name;
  university: UniversityDto = {
    name: '',
  };

  onSubmit() {
    this.api.postUniversity(this.university).subscribe((data) => {
      this.messageService.add({
        key: 'toast',
        severity: 'success',
        summary: 'Success',
        detail: 'University created',
        life: 1000,
      });
      //this.router.navigate(['/find/universities']);
    });
    this.messageService.add({
      severity: 'error',
      summary: 'Error',
      detail: 'Error creating University',
    });
  }

  onCancel() {
    this.router.navigate(['/find/universities']);
  }
}
