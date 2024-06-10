import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  NgModel,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';
import { InputTextModule } from 'primeng/inputtext';
import { ApiService } from '../service/api.service';
import { UniversityDto, UniversityGetDto } from '../constants/types';
import { Router } from '@angular/router';

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
    private formBuilder: FormBuilder
  ) {
    this.form = formBuilder.group({
      Name: ['', Validators.required],
    });
  }

  response: UniversityGetDto | undefined;
  form: FormGroup;
  name: string = NgModel.name;
  university: UniversityDto = {
    name: '',
  };

  onSubmit() {
    this.api
      .postUniversity(this.university)
      .subscribe((data) => (this.response = { ...data }));
    if (this.response != undefined) {
      this.router.navigate([`/find/universities`]);
    } else {
    }
  }

  onCancel() {
    this.router.navigate(['/find/universities']);
  }
}
