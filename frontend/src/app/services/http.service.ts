import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  BASE_URL = 'http://localhost:8080/'

  constructor(private http: HttpClient) { }

  getAllPosts(): Observable<any> {
    return this.http.get<any>(this.BASE_URL + 'post/all')
  }
}
