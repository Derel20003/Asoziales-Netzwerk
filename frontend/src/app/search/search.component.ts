import { Component, OnInit } from '@angular/core';
import {HttpService} from "../services/http.service";
import {BehaviorSubject, debounceTime, delay, distinctUntilChanged, filter, Subject, take} from "rxjs";
import {Post} from "../model/post.model";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  query: BehaviorSubject<string> = new BehaviorSubject<string>('');
  posts: Post[] = [];

  constructor(private http: HttpService) { }

  ngOnInit(): void {
    this.query.pipe(
      filter(query => query.length >= 3),
      debounceTime(500),
      distinctUntilChanged()
    ).subscribe(query => this.search(query))
  }

  search(query: string) {
    this.http.searchPosts(query).subscribe(posts => {
      console.log(posts)
      this.posts = posts;
    });
  }
}
