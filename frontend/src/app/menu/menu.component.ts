import {AfterContentChecked, AfterViewInit, Component, OnInit} from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {PostDialogComponent} from "../post/post-dialog/post-dialog.component";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements AfterContentChecked {
  title: String = '';
  loggedIn: boolean = false

  constructor(private breakpointObserver: BreakpointObserver,
              private router: Router,
              private dialog: MatDialog) {
  }

  ngAfterContentChecked() {
    this.changeTitle()
  }

  changeTitle() {
    let url = this.router.url
    let title = url == '/' ? 'auth' : url.split('/')[1]
    if (title.includes('auth')) title = 'authentication'
    else this.loggedIn = true
    this.title = title[0].toUpperCase() + title.slice(1)
  }

  createPost() {
    this.dialog.open(PostDialogComponent, {
      width: '600px',
      maxWidth: '700px'
    })
  }
}
