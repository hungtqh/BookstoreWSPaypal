import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { routing } from './app.routing';
import 'hammerjs'
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { LoginComponent } from './components/login/login.component';
import { LoginService } from './services/login.service'
import { MatInputModule, MatGridListModule, MatCardModule, MatMenuModule, MatIconModule, MatAutocompleteModule, MatFormFieldModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { AddNewBookComponent } from './components/add-new-book/add-new-book.component';
import {MatSelectModule} from '@angular/material/select';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { AddBookService } from './services/add-book.service';
import { UploadImageService } from './services/upload-image.service';
import { BookListComponent, DialogResultExampleDialog } from './components/book-list/book-list.component';
import { GetBookListService } from './services/get-book-list.service';
import { ViewBookComponent } from './components/view-book/view-book.component';
import { GetBookService } from './services/get-book.service';
import {MatListModule} from '@angular/material/list';
import { EditBookComponent } from './components/edit-book/edit-book.component';
import { EditBookService } from './services/edit-book.service';
import { RemoveBookService } from './services/remove-book.service';
import {MatDialogModule} from '@angular/material/dialog';


@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    LoginComponent,
    AddNewBookComponent,
    BookListComponent,
    DialogResultExampleDialog,
    ViewBookComponent,
    EditBookComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatToolbarModule,
    MatGridListModule,
    MatInputModule,
    MatFormFieldModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatListModule,
    MatDialogModule,
    routing,
    HttpModule,
    FormsModule
  ],

  providers: [
    LoginService,
    AddBookService,
    UploadImageService,
    GetBookListService,
    GetBookService,
    EditBookService,
    RemoveBookService
  ],
  bootstrap: [AppComponent, DialogResultExampleDialog]
})
export class AppModule { }
