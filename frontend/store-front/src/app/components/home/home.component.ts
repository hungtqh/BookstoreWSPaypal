import { Component, OnInit } from '@angular/core';
import { AppConst } from 'src/app/constants/app-const';
import { Book } from 'src/app/models/book';
import { BookService } from 'src/app/services/book.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private serverPath = AppConst.serverPath;
  public newBookList: Book[] = [];
  public selectedBook: Book;

  constructor(private router: Router,
              private bookService: BookService) { }

  ngOnInit() {

    this.bookService.getNewBookList().subscribe(
      res => {
        console.log(res.json());
        this.newBookList = res.json();
      },
      error => {
        console.log(error.text());
      }
    );
  }

  onSelect(book: Book) {
    this.selectedBook = book;
    this.router.navigate(['/bookDetail', this.selectedBook.id]);
  }
}
