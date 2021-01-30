import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {UserViewComponent} from '../pages/user-view/user-view.component';
import {RoutingHeaderComponent} from '../headers/routing-header/routing-header.component';
import {TaskViewComponent} from '../pages/task-view/task-view.component';
import {UserEditComponent} from '../pages/user-edit/user-edit.component';
import {TaskEditComponent} from '../pages/task-edit/task-edit.component';

const  routes: Routes = [
  {path: '', component: RoutingHeaderComponent},
  {path: 'UserView', component: UserViewComponent },
  {path: 'TaskView/:id', component: TaskViewComponent},
  {path: 'UserEdit', component: UserEditComponent},
  {path: 'TaskEdit/:id', component: TaskEditComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [RoutingHeaderComponent, UserViewComponent, TaskViewComponent, TaskEditComponent , UserEditComponent];
