import { Component } from '@angular/core';
import { MenubarModule } from 'primeng/menubar';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-menu-bar',
  standalone: true,
  imports: [MenubarModule],
  templateUrl: './menu-bar.component.html',
  styleUrl: './menu-bar.component.css',
})
export class MenuBarComponent {
  items: MenuItem[];

  constructor() {
    this.items = [
      {
        label: 'Find Courses',
        icon: 'pi pi-search',
        routerLink: '/find/universities',
      },
      {
        label: 'Professors',
        icon: 'pi pi-users',
        routerLink: '/professors',
      },
    ];
  }
}
