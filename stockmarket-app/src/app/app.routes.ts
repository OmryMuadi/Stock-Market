import { Routes } from '@angular/router';
import { StockComponent } from './stock-component/stock-component';
import { PageNotFoundComponent } from './page-not-found-component/page-not-found-component';
import { PortfolioComponent } from './portfolio-component/portfolio-component';

export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: PortfolioComponent },
    { path: 'stock/:id', component: StockComponent },
    { path: '**', component: PageNotFoundComponent },
];
