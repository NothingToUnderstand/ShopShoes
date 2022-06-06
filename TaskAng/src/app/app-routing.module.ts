import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { AuthGuardService } from './service/auth.guard.service';
import { CreateShoesComponent } from './shoes/createShoes/createShoes.component';
import { EditShoesComponent } from './shoes/editShoes/editShoes.component';
import { ListShoesComponent } from './shoes/listShoes/listShoes.component';
import { ShoesInfoComponent } from './shoes/shoesInfo/shoesInfo.component';
import { CreateUserComponent } from './user/createUser/createUser.component';
import { EditUserComponent } from './user/editUser/editUser.component';
import { ListUsersComponent } from './user/listUsers/listUsers.component';
import { LoginComponent } from './user/login/login.component';
import { UserAccountComponent } from './user/userAccount/userAccount.component';
import { UserInfoComponent } from './user/userInfo/userInfo.component';
import { WelcomeComponent } from './welcome/welcome/welcome.component';
import { WelcomeRestComponent } from './welcomeRest/welcomeRest/welcomeRest.component';


const routes: Routes = [
  {path:'welcome',component:WelcomeRestComponent},
  {path:'',component:WelcomeComponent},
  {path:'shoes',component:ListShoesComponent,canActivate:[AuthGuardService]},
  {path:'createShoes',component:CreateShoesComponent,canActivate:[AuthGuardService]},
  {path:'editShoes/:id',component:EditShoesComponent,canActivate:[AuthGuardService]},
  {path:'infoShoes/:id',component:ShoesInfoComponent,canActivate:[AuthGuardService]},
  {path:'login',component:LoginComponent},
  {path:'infoUser/:id',component:UserInfoComponent,canActivate:[AuthGuardService]},
  {path:'createUser',component:CreateUserComponent,canActivate:[AuthGuardService]},
  {path:'account',component:UserAccountComponent,canActivate:[AuthGuardService]},
  {path:'users',component:ListUsersComponent,canActivate:[AuthGuardService]},
  {path:'editUser/:id',component:EditUserComponent,canActivate:[AuthGuardService]},
  

  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
