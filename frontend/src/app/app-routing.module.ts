import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {CatsComponent} from "./cats/cats.component";
import {SearchComponent} from "./search/search.component";
import {UsersComponent} from "./users/users.component";
import {AuthComponent} from "./auth/auth.component";

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'search', component: SearchComponent},
  {path: 'users', component: UsersComponent},
  {path: 'cats', component: CatsComponent},
  {path: 'auth', component: AuthComponent},
  {path: '**', redirectTo: 'home'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
