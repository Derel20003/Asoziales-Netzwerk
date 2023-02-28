import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Interaction, InteractionType, Post} from "../model/post.model";
import {HttpService} from "../services/http.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  @Input("post") post: Post = {id: '', _id: '', content: 'test', comments: [], interactions: [], timestamp: new Date(), userId: ''};
  @Output() updateEvent = new EventEmitter<any>();
  filterDislikes(i: Interaction): boolean {
    return i.type == InteractionType.DISLIKE
  }

  filterHates(i: Interaction): boolean {
    return i.type == InteractionType.HATE
  }

  constructor(private http: HttpService,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }

  dislike() {
    this.http.interact(this.post.id ?? this.post._id, InteractionType.DISLIKE)
      .subscribe(() => {
        this.updateEvent.emit('dislike')
      });
  }

  hate() {
    this.http.interact(this.post.id ?? this.post._id, InteractionType.HATE)
      .subscribe(() => {
        this.updateEvent.emit('hate')
      });
  }

  comment() {
    // TODO: build comment function
    this.snackBar.open('Currently unavailable', 'Close', {
      duration: 3000,
      panelClass: 'snack-bar',
    })
  }
}
