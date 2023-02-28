import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  SERVER_URL = 'http://localhost:8080/auth/';

  constructor(private http: HttpClient) {
  }

  login(email: string, password: string): Observable<any> {
    return this.http.post(this.SERVER_URL, {email: email});
  }

  isLoggedIn(): boolean {
    const n = new Date().getTime() / 1000;
    const exp = Number(sessionStorage.getItem('expires_at'));
    return n < exp;
  }

  logout(): void {
    sessionStorage.removeItem('id_token');
    sessionStorage.removeItem('expires_at');
  }
}
