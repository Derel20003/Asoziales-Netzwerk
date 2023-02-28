import {AfterContentChecked, AfterContentInit, AfterViewInit, Component, OnInit} from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import {Router} from "@angular/router";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements AfterContentChecked {
  title: String = '';

  constructor(private breakpointObserver: BreakpointObserver, private router: Router) {
  }

  ngAfterContentChecked() {
    this.changeTitle()
  }

  changeTitle() {
    let title = this.router.url.split('/')[1]
    this.title = title[0].toUpperCase() + title.slice(1)
  }
}
