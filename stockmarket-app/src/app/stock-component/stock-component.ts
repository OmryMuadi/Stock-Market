import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StockService } from '../stock-service';

@Component({
  selector: 'app-stock-component',
  imports: [],
  templateUrl: './stock-component.html',
  styleUrls: ['./stock-component.css'],
})
export class StockComponent implements OnInit {
  // the activated route and router are named clearly
  activatedRoute = inject(ActivatedRoute);
  router = inject(Router);
  service = inject(StockService);

  currentStock: any;
  currentStockPrice = 0;
  currentStockShares = 0;
  currentStockSymbol = '';

  ngOnInit() {
    const id = this.activatedRoute.snapshot.params['id'];
    this.currentStock = this.service.getStockById(id);

    if (!this.currentStock) {
      // navigate to wildcard route (page not found) or home
      this.router.navigate(['/error-stock-not-found']);
      return;
    }

    this.currentStockPrice = this.currentStock.price;
    this.currentStockShares = this.currentStock.shares;
    this.currentStockSymbol = this.currentStock.symbol;
  }
}
