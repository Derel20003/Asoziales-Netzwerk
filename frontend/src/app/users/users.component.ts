import { Component, OnInit } from '@angular/core';
import {HttpService} from "../services/http.service";
import {User} from "../model/user.model";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  users: User[] = [];

  constructor(private http: HttpService) {
  }

  ngOnInit(): void {
    this.http.getAllUsers().subscribe(users => {
      this.users = users;
    })
  }

}
