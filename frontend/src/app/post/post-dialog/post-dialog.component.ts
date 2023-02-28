import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {HttpService} from "../../services/http.service";

@Component({
  selector: 'app-post-dialog',
  templateUrl: './post-dialog.component.html',
  styleUrls: ['./post-dialog.component.css']
})
export class PostDialogComponent implements OnInit {
  postContent: String = '';

  constructor(private http: HttpService,
              public dialogRef: MatDialogRef<PostDialogComponent>,) { }

  ngOnInit(): void {
  }

  post() {
    this.http.post(this.postContent).subscribe(() => {
      this.http.postChanged$.next(this.http.postChanged$.value + 1);
    })
  }
}
