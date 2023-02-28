import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Post} from "../model/post.model";
import {User} from "../model/user.model";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  BASE_URL = 'http://localhost:8080/'

  constructor(private http: HttpClient) { }

  getAllPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(this.BASE_URL + 'post/all')
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.BASE_URL + 'user/all')
  }

  getUserProfile(id: string): Observable<User> {
    return this.http.get<User>(this.BASE_URL + 'user/profile/' + id)
  }

  interact(id: string, interaction: string): Observable<any> {
    return this.http.post(this.BASE_URL + 'post/interact', {postId: id, type: interaction})
  }

  post(content: String): Observable<any> {
    return this.http.post(this.BASE_URL + 'post/', {content: content})
  }

  searchPosts(query: string): Observable<Post[]>  {
    return this.http.get<Post[]>(this.BASE_URL + 'post/find?content=' + query);
  }

  getUser(): Observable<User> {
    return this.http.get<User>(this.BASE_URL + 'user');
  }

  postUser(user: User): Observable<any> {
    return this.http.put(this.BASE_URL + 'user', user);
  }
}
