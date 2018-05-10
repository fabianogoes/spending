import { RegistryComponent } from './registry/registry.component';
import { Routes, RouterModule } from '@angular/router';
import { DashComponent } from './dash/dash.component';
import { CategoryComponent } from './category/category.component';
import { TypeComponent } from './type/type.component';
import { UploadComponent } from './upload/upload.component';
const MAINMENU_ROUTES: Routes = [
    { path: '', redirectTo: '/dash', pathMatch: 'full' },
    { path: 'dash', component: DashComponent },
    { path: 'registry', component: RegistryComponent },
    { path: 'registry/category/:categoryId', component: RegistryComponent },
    { path: 'registry/type/:typeId', component: RegistryComponent },
    { path: 'category', component: CategoryComponent },
    { path: 'type', component: TypeComponent },
    { path: 'upload', component: UploadComponent }
];
export const CONST_ROUTING = RouterModule.forRoot(MAINMENU_ROUTES, { useHash: true });
