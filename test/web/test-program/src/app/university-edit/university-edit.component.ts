import { Component, OnInit } from '@angular/core';
import { ApiService } from '../service/api.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastService } from '../service/toast.service';
import { UniversityDto, UniversityGetDto } from '../constants/types';
import { InputTextModule } from 'primeng/inputtext';
import { DividerModule } from 'primeng/divider';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-university-edit',
  standalone: true,
  imports: [
    InputTextModule,
    DividerModule,
    FormsModule,
    ButtonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './university-edit.component.html',
  styleUrl: './university-edit.component.css',
})
export class UniversityEditComponent implements OnInit {
  constructor(
    private api: ApiService,
    private router: Router,
    private toaster: ToastService,
    private route: ActivatedRoute
  ) {}

  university: UniversityGetDto | undefined;
  editedUniversity: UniversityDto = {
    name: '',
  };

  ngOnInit() {
    this.api
      .getUniversity(Number(this.route.snapshot.paramMap.get('id')))
      .subscribe((data) => {
        this.university = { ...data };
        this.editedUniversity.name = data.name;
      });
  }

  onSubmit() {
    this.api
      .putUniversity(this.editedUniversity, this.university?.id || 0)
      .subscribe((data) => {
        this.toaster.showToast('success', 'Success', 'University Edited');
        this.router.navigate([`/university/${this.university?.id}`]);
      });
  }

  onCancel() {
    this.router.navigate([`/university/${this.university?.id}`]);
  }
}
