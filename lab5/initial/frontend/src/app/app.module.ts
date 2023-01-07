import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MoviesComponent} from "./movie/movies.component";
import {StarRatingModule} from 'angular-star-rating';
import {AboutComponent} from "./about/about.component";
import {MovieComponent} from './movie/movie.component';
import {DirectorsComponent} from "./director/directors.component";
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    MoviesComponent,
    MovieComponent,
    DirectorsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    StarRatingModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
