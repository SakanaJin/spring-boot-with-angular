import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root',
})
export class ToastService {
  constructor(private messageService: MessageService) {}

  TOAST_KEY: string = '1';

  async showToast(
    severity: string,
    summary: string,
    detail: string
  ): Promise<void> {
    this.messageService.add({
      key: this.TOAST_KEY,
      severity: severity,
      summary: summary,
      detail: detail,
    });
  }
}
