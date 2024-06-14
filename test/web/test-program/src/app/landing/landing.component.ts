import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ApiService } from '../service/api.service';
import { Quote } from '../constants/types';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-landing',
  standalone: true,
  imports: [RouterLink, NgIf],
  templateUrl: './landing.component.html',
  styleUrl: './landing.component.css',
})
export class LandingComponent implements OnInit {
  constructor(private api: ApiService) {}

  quote: Quote | undefined;

  ngOnInit() {
    this.api.getQuote().subscribe((data) => (this.quote = { ...data }));
  }
}
