import {Component, OnInit} from '@angular/core';
import {FavoriteCat, User} from "../model/user.model";
import {HttpService} from "../services/http.service";


@Component({
  selector: 'app-cats',
  templateUrl: './cats.component.html',
  styleUrls: ['./cats.component.css']
})
export class CatsComponent implements OnInit {
  cats: FavoriteCat[] = [
    {name: 'Abyssinian', imgURL: 'https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg'},
    {name: 'Burmese', imgURL: 'https://cdn2.thecatapi.com/images/hj7Oh-SRY.jpg'},
    {name: 'Sphynx', imgURL: 'https://cdn2.thecatapi.com/images/Br8qCwbS9.jpg'},
    {name: 'Persian', imgURL: 'https://cdn2.thecatapi.com/images/AYfFc4vOB.jpg'},
  ];
  user?: User;

  constructor(private http: HttpService) {
    this.refresh();
  }

  ngOnInit(): void {
  }

  toggle(cat: FavoriteCat) {
    if (!this.user)
      return;

    if (this.includes(cat)) {
      let c = this.user.favoriteCats.filter((c) => c.name == cat.name && c.imgURL == cat.imgURL)[0];
      let i = this.user.favoriteCats.indexOf(c ?? cat) ?? -1;
      if (i >= 0) {
        this.user?.favoriteCats.splice(i, 1);
      }
    } else {
      this.user?.favoriteCats.push(cat);
    }

    this.http.postUser(this.user).subscribe(() => this.refresh())
  }

  includes(cat: FavoriteCat): boolean {
    if (this.user && this.user.favoriteCats)
      return (this.user.favoriteCats.filter((c) => c.name == cat.name && c.imgURL == cat.imgURL).length > 0) ?? false;
    return false;
  }

  private refresh() {
    this.http.getUser().subscribe(user => {
      this.user = user;
    });
  }
}
