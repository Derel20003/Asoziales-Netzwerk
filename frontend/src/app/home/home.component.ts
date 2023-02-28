import { Component, OnInit } from '@angular/core';
import {HttpService} from "../services/http.service";
import {Post} from "../model/post.model";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  posts: Post[] = [];

  constructor(private http: HttpService) { }

  ngOnInit(): void {
    this.refresh();
  }

  refresh() {
    this.http.getAllPosts().subscribe((posts) => {
      this.posts = posts;
    });
  }
}
