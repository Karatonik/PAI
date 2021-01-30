import {Component, OnInit, Output, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {User, UserService} from '../../services/user.service';
import {MatPaginator} from '@angular/material/paginator';
import {Router} from '@angular/router';
import {Status, Task, TaskService} from '../../services/task.service';

@Component({
  selector: 'app-task-view',
  templateUrl: './task-view.component.html',
  styleUrls: ['./task-view.component.css']
})
export class TaskViewComponent implements OnInit {
  constructor(private taskService: TaskService, public router: Router){}
  displayedColumns: string[] = ['id', 'title', 'description', 'dateAdded', 'type', 'status', 'userId'];
  dataSource: MatTableDataSource<Task>;
  root: Task[];
  @Output() taskRow: Task;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  id = -1;
  // tslint:disable-next-line:use-lifecycle-interface
  edited = false;
  status: Status;
  ngOnInit(): void {
    this.getAllData();
    // tslint:disable-next-line:only-arrow-functions
    this.dataSource.filterPredicate = function(data, filter: string): boolean {
      return data.title.toLowerCase().includes(filter);
    };
  }
  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }
  applyFilter(filterValue: string): void {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }
  onRowClick(row): void {
    this.taskRow = row;
    this.id = this.taskRow.taskId;
    this.edited = true;
  }
  deleteRecord(): void {
   this.taskService.deleteTaskById(this.id).subscribe();
   window.location.reload();
  }
  getAllData(): void {
      this.taskService.getTasks().subscribe(value => {
        this.root = value;
        this.dataSource = new MatTableDataSource<Task>(this.root);
      });
    }

  ChangeStatus(): void {
   this.taskService.changeTaskStatus(this.id , this.status).subscribe();
    window.location.reload();
  }
}
