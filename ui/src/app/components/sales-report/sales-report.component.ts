import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {CartService} from '@app/services/cart.service';
import {ProductService} from '@app/services/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ReportService} from '@app/services/report.service';
import {throwError} from 'rxjs';

@Component({
  selector: 'app-sales-report',
  templateUrl: './sales-report.component.html',
  styleUrls: ['./sales-report.component.css']
})
export class SalesReportComponent implements OnInit {
  @Output() onCreate: EventEmitter<any> = new EventEmitter<any>();
  public product;
  public reportItems;
  public sum;
  validMessage: any;

  constructor(private reportService: ReportService,
              private productService: ProductService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.getCartItems(this.route.snapshot.params.time);
    this.calculate(this.route.snapshot.params.time);
  }

  getCartItems(time) {
    this.reportService.getSalesReport(time).subscribe(
      data => {
        this.reportItems = data;
      },
      error => {
        this.validMessage = 'There are no products sold for the selected period of time.';
        return throwError(error.message || error);
      }
    );
  }

  calculate(time) {
    console.log('calculate:' + time);
    this.reportService.calculate(time).subscribe(
      data => {
        this.sum = data;
      },
      error => console.error(error),
      () => console.log('Items Loaded')
    );
  }
}
