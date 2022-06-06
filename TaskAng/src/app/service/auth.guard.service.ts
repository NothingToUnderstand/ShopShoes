// AuthGuardService

import { Injectable } from "@angular/core";
import { CanActivate, CanLoad, Router } from "@angular/router";
import { AuthService } from "./auth.service";

@Injectable({
  providedIn: 'root',
})
export class AuthGuardService implements CanActivate { //CanLoad

  constructor(private authService: AuthService, private router: Router) {}

  public canActivate(/* route: ActivatedRouteSnapshot, state: RouterStateSnapshot */): boolean {
    return this.isAuthenticated();
  }

  // public canLoad(): boolean {
  //   return this.isAuthenticated();
  // }

  private isAuthenticated(): boolean {
    if (!this.authService.isAuthenticated()) {
      alert('You are not allowed to view this page');
      // this.authService.login();
      this.router.navigate(['/login']); //redirect on login
      return false;
    }

    return true;
  }
}