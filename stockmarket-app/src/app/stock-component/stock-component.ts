import { Component } from '@angular/core';

@Component({
  selector: 'app-stock-component',
  imports: [],
  templateUrl: './stock-component.html',
  styleUrl: './stock-component.css',
})
export class StockComponent {
  currentStockPrice: number = 100; // Example stock price
  stockName: string = "Example Stock";
  stockSymbol: string = "EXM";

}
