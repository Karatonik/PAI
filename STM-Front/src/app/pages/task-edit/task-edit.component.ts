import { Component, OnInit } from '@angular/core';
import {Status, TaskService, Type} from '../../services/task.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-task-edit',
  templateUrl: './task-edit.component.html',
  styleUrls: ['./task-edit.component.css']
})
export class TaskEditComponent implements OnInit {
title: string;
description: string;
type: Type;
status: Status;
id: number;
  constructor(private taskService: TaskService , private activatedRoute: ActivatedRoute) {
    this.activatedRoute.params.subscribe(value => this.id = value.id);
  }

  ngOnInit(): void {
  }
  goBack(): void {
    window.history.back();
  }
  setTask(): void {
    console.log(this.type + '  ' + this.status);
    this.taskService.createTask(this.title, this.description, this.type , this.status, this.id).subscribe();
  }

}
