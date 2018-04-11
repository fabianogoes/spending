import { Routes, RouterModule } from '@angular/router';
import { DashComponent } from './dash.component';
const MAINMENU_ROUTES: Routes = [
    { path: '', redirectTo: '/dash', pathMatch: 'full' },
    { path: 'dash', component: DashComponent }
];
export const CONST_ROUTING = RouterModule.forRoot(MAINMENU_ROUTES, { useHash: true });
