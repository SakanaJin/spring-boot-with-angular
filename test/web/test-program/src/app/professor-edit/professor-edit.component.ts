import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';
import { InputTextModule } from 'primeng/inputtext';
import { ApiService } from '../service/api.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastService } from '../service/toast.service';
import { ProfessorDto, ProfessorGetDto } from '../constants/types';

@Component({
  selector: 'app-professor-edit',
  standalone: true,
  imports: [
    InputTextModule,
    DividerModule,
    FormsModule,
    ButtonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './professor-edit.component.html',
  styleUrl: './professor-edit.component.css',
})
export class ProfessorEditComponent implements OnInit {
  constructor(
    private api: ApiService,
    private router: Router,
    private toaster: ToastService,
    private route: ActivatedRoute
  ) {}

  professor: ProfessorGetDto | undefined;
  editedProfessor: ProfessorDto = {
    name: '',
  };

  ngOnInit() {
    this.api
      .getProfessor(Number(this.route.snapshot.paramMap.get('id')))
      .subscribe((data) => {
        this.professor = { ...data };
        this.editedProfessor.name = data.name;
      });
  }

  onSubmit() {
    this.api
      .putProfessor(this.editedProfessor, this.professor?.id || 0)
      .subscribe((data) => {
        this.toaster.showToast('success', 'Success', 'Professor Updated');
        this.router.navigate(['/professors']);
      });
  }

  onCancel() {
    this.router.navigate(['/professors']);
  }
}
