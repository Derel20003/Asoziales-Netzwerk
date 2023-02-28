import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {
  title: String = 'Home';

  constructor(private breakpointObserver: BreakpointObserver, private router: Router) {
  }

  changeTitle() {
    let title = this.router.url.split('/')[1]
    this.title = title[0].toUpperCase() + title.slice(1)
  }
}
