import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {CatsComponent} from "./cats/cats.component";
import {SearchComponent} from "./search/search.component";
import {UsersComponent} from "./users/users.component";
import {AuthComponent} from "./auth/auth.component";
import {AuthGuardService} from "./services/auth-guard.service";
import {UserDetailComponent} from "./users/user-detail/user-detail.component";

const routes: Routes = [
  {path: 'home', component: HomeComponent, canActivate: [AuthGuardService]},
  {path: 'search', component: SearchComponent, canActivate: [AuthGuardService]},
  {path: 'users', component: UsersComponent, canActivate: [AuthGuardService]},
  {path: 'users/:id', component: UserDetailComponent, canActivate: [AuthGuardService]},
  {path: 'cats', component: CatsComponent, canActivate: [AuthGuardService]},
  {path: 'auth', component: AuthComponent},
  {path: '**', redirectTo: 'home'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
