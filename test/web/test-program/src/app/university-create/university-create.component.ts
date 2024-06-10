import { Component } from '@angular/core';
import { FormsModule, NgModel } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DividerModule } from 'primeng/divider';
import { InputTextModule } from 'primeng/inputtext';
import { ApiService } from '../service/api.service';
import { UniversityDto, UniversityGetDto } from '../constants/types';
import { Router } from '@angular/router';

@Component({
  selector: 'app-university-create',
  standalone: true,
  imports: [InputTextModule, DividerModule, FormsModule, ButtonModule],
  templateUrl: './university-create.component.html',
  styleUrl: './university-create.component.css',
})
export class UniversityCreateComponent {
  constructor(private api: ApiService, private router: Router) {}

  response: UniversityGetDto | undefined;
  name: string = NgModel.name;
  university: UniversityDto = {
    name: '',
  };

  onSubmit() {
    this.api
      .postUniversity(this.university)
      .subscribe((data) => (this.response = { ...data }));

    this.router.navigate([`/find/universities`]);
  }
}
