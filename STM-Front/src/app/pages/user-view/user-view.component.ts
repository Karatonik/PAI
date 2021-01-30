import {Component, OnInit, Output, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {User, UserService} from '../../services/user.service';
import {MatPaginator} from '@angular/material/paginator';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-view',
  templateUrl: './user-view.component.html',
  styleUrls: ['./user-view.component.css']
})
export class UserViewComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'lastName', 'email', 'password', 'status', 'registrationDateTime'];
  dataSource: MatTableDataSource<User>;
  @Output() userRow: User;
  @ViewChild(MatPaginator , {static: false}) paginator: MatPaginator;
  root: User[];
  id = -1;
  constructor(private userService: UserService, public router: Router) { }

  // tslint:disable-next-line:use-lifecycle-interface
  edited = false;
  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  ngOnInit(): void {
    this.getAllData();
    // tslint:disable-next-line:only-arrow-functions
    this.dataSource.filterPredicate = function(data, filter: string): boolean {
      return data.name.toLowerCase().includes(filter);
    };
  }
  applyFilter(filterValue: string): void {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }
  deleteRecord(): void {
    this.userService.deleteUserById(this.id).subscribe();
    this.getAllData();
  }
  onRowClick(row): void {
    this.userRow = row;
    this.id = this.userRow.userId;
    this.edited = true;
  }
  createUser(): void {
    this.router.navigate(['UserEdit']);
  }
  getAllData(): void {
    // tslint:disable-next-line:triple-equals
    this.userService.getUsers().subscribe(value => {
      this.root = value;
      this.dataSource = new MatTableDataSource<User>(this.root);
    });
  }
  changeActivity(): void {
    this.userService.activateUser(this.id).subscribe();
    window.location.reload();
  }

  createTask(): void  {
    this.router.navigate(['TaskEdit', this.id]);
  }
}
