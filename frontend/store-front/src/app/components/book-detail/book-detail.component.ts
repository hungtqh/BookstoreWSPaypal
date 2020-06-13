import { Component, OnInit } from '@angular/core';
import { Book } from '../../models/book';
import { BookService } from '../../services/book.service';
import { CartService } from '../../services/cart.service';
import {Params, ActivatedRoute, Router} from '@angular/router';
import {Http} from '@angular/http';
import {AppConst} from '../../constants/app-const';

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {

	private bookId: number;
	private book: Book = new Book();
	private serverPath = AppConst.serverPath;
	private numberList: number[] = [1,2,3,4,5,6,7,8,9];
	private qty: number;

	private addBookSuccess: boolean = false;
	private notEnoughStock:boolean = false;

  constructor(
  	private bookService:BookService,
    private cartService: CartService,
		private router:Router,
		private http:Http,
		private route:ActivatedRoute
  	) { }

  onAddToCart() {
    this.cartService.addItem(this.bookId, this.qty).subscribe(
      res => {
        console.log(res.text());
        this.addBookSuccess=true;
      },
      err => {
				this.router.navigate(['/myAccount']);
        console.log(err.text());
        // this.notEnoughStock=true;
      }
    );
  }

  ngOnInit() {
  	this.route.params.forEach((params: Params) => {
  		this.bookId = Number.parseInt(params['id']);
  	});

  	this.bookService.getBook(this.bookId).subscribe(
  		res => {
  			this.book=res.json();
  		},
  		error => {
  			console.log(error);
  		}
  	);

  	this.qty = 1;
  }

}
