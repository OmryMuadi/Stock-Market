import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class StockService {
  tableData = [
    { id: "1", symbol: 'AAPL', shares: 50, price: 150 },
    { id: "2", symbol: 'GOOGL', shares: 30, price: 2800 },
    { id: "3", symbol: 'AMZN', shares: 20, price: 3400 },
    { id: "4", symbol: 'MSFT', shares: 40, price: 300 },
  ];

  getStockById(id: string) {
    return this.tableData.find(stock => stock.id === id);
  }

  getAllStocks() {
    return this.tableData;
  }
}
