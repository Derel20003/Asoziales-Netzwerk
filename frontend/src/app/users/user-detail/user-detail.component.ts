import { Component, OnInit } from '@angular/core';
import {HttpService} from "../../services/http.service";
import {ActivatedRoute} from "@angular/router";
import {User} from "../../model/user.model";

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

  user?: User;
  id: string = '';

  constructor(private http: HttpService, private route: ActivatedRoute) {
    route.paramMap.subscribe(map => {
      this.id = map.get("id") ?? '';
      this.refresh();
    })
  }

  ngOnInit(): void {
  }

  refresh() {
    this.http.getUserProfile(this.id).subscribe(res => {
      this.user = res;
    })
  }
}
