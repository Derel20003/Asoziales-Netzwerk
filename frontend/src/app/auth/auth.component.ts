import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private snackBar: MatSnackBar, private router: Router) {
    this.loginForm = this.fb.group({
      email: [null, Validators.email],
      password: [null, Validators.required]
    });
  }

  login(): void {
    const val = this.loginForm.value;
    this.authService.login(val.email, val.password).subscribe({
      next: value => {
        sessionStorage.setItem('id_token', value.token);
        sessionStorage.setItem('expires_at', value.expires_at);
        this.router.navigateByUrl('/res-list');
      }, error: err => {
        this.authService.logout();
        this.snackBar.open(`Anmelden fehlgeschlagen: ${err.message}`, undefined,
          {duration: 3000, panelClass: 'snackbar-dark'});
      }
    });
  }

  ngOnInit(): void {
  }

}
