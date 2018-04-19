import { RegistryComponent } from './registry.component';
import { Routes, RouterModule } from '@angular/router';
import { DashComponent } from './dash.component';
import { CategoryComponent } from './category.component';
import { TypeComponent } from './type/type.component';
const MAINMENU_ROUTES: Routes = [
    { path: '', redirectTo: '/dash', pathMatch: 'full' },
    { path: 'dash', component: DashComponent },
    { path: 'registry', component: RegistryComponent },
    { path: 'category', component: CategoryComponent },
    { path: 'type', component: TypeComponent }
];
export const CONST_ROUTING = RouterModule.forRoot(MAINMENU_ROUTES, { useHash: true });
