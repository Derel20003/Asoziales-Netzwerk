import {Component, Input, OnInit} from '@angular/core';
import {Interaction, Post} from "../model/post.model";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  @Input("post") post: Post = {content: 'test', comments: [], interactions: [], timestamp: new Date(), userId: ''};
  filterDislikes(i: Interaction): boolean {
    return i.type == 'DISLIKE'
  }

  filterHates(i: Interaction): boolean {
    return i.type == 'HATE'
  }

  constructor() { }

  ngOnInit(): void {
  }



}
