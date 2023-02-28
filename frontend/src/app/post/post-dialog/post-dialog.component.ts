import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-post-dialog',
  templateUrl: './post-dialog.component.html',
  styleUrls: ['./post-dialog.component.css']
})
export class PostDialogComponent implements OnInit {
  postContent: String = '';

  constructor(public dialogRef: MatDialogRef<PostDialogComponent>) { }

  ngOnInit(): void {
  }

}
