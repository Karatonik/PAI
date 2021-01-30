import { Component, OnInit } from '@angular/core';
import {User, UserService} from '../../services/user.service';

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {
name: string;
lastName: string;
email: string;
password: string;
  constructor(private userService: UserService ) {
  }

  ngOnInit(): void {
  }
  goBack(): void {
    window.history.back();
  }
  setUser(): void{
    this.userService.createUser(this.name, this.lastName, this.email, this.password).subscribe();
  }

}
