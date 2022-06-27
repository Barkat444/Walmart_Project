import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { WishlistComponent } from './components/wishlist/wishlist.component';
import { PageErrorComponent } from './components/page-error/page-error.component';
import { AuthGuard } from './guards/auth.guard';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { LandingpageComponent } from './components/landingpage/landingpage.component';

const routes: Routes = [
{path:'',component:LandingpageComponent},
{path:'dashboard',component:DashboardComponent},  
{path:'login',component:LoginComponent},
{path:'register',component:RegisterComponent},
{path:'edit-profile',component:EditProfileComponent,canActivate:[AuthGuard]},
{path:'landingpage',component:LandingpageComponent},
{path:'wishlist',component:WishlistComponent,canActivate:[AuthGuard]},
{path:'**',component:PageErrorComponent},



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
