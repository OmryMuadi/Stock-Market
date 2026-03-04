import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-portfolio-component',
  imports: [CommonModule],
  templateUrl: './portfolio-component.html',
  styleUrl: './portfolio-component.css',
})
export class PortfolioComponent {
  router = inject(Router);

  tableData = [
    { id: "1", stock: 'AAPL', shares: 50, price: 150 },
    { id: "2", stock: 'GOOGL', shares: 30, price: 2800 },
    { id: "3", stock: 'AMZN', shares: 20, price: 3400 },
    { id: "4", stock: 'MSFT', shares: 40, price: 300 },
  ];

  onRowClick(stock: any) {
    this.router.navigate(['/stock', stock.id]);
  }
}
