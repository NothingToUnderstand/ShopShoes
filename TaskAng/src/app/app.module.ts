
import { BrowserModule } from '@angular/platform-browser';
import{ HttpClientModule}from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateShoesComponent } from './shoes/createShoes/createShoes.component';
import { ListShoesComponent } from './shoes/listShoes/listShoes.component';
import { EditShoesComponent } from './shoes/editShoes/editShoes.component';
import { ShoesServiceService } from './shoes/service/shoesService.service';
import { ShoesInfoComponent } from './shoes/shoesInfo/shoesInfo.component';
import { CreateUserComponent } from './user/createUser/createUser.component';
import { ListUsersComponent } from './user/listUsers/listUsers.component';
import { UserAccountComponent } from './user/userAccount/userAccount.component';
import { UserInfoComponent } from './user/userInfo/userInfo.component';
import { EditUserComponent } from './user/editUser/editUser.component';
import { WelcomeComponent } from './welcome/welcome/welcome.component';
import { WelcomeRestComponent } from './welcomeRest/welcomeRest/welcomeRest.component';
import { ShoesNavComponent } from './shoes/navigation/shoesNav/shoesNav.component';
import { UserNavComponent } from './user/navigation/userNav/userNav.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { LoginComponent } from './user/login/login.component';




@NgModule({
  declarations: [	
    AppComponent,
    CreateShoesComponent,
    ListShoesComponent,
    EditShoesComponent,
    ShoesInfoComponent,
    CreateUserComponent,
    ListUsersComponent,
    UserAccountComponent,
    UserInfoComponent,
    EditUserComponent,
    WelcomeComponent,
    WelcomeRestComponent,
    ShoesNavComponent,
    UserNavComponent,
    LoginComponent
    
   ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
    
  ],
  providers: [ShoesServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
