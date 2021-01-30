package pl.mateusz.kalksztejn.STM.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.mateusz.kalksztejn.STM.models.Task;
import pl.mateusz.kalksztejn.STM.models.User;
import pl.mateusz.kalksztejn.STM.models.enums.Status;
import pl.mateusz.kalksztejn.STM.models.enums.Type;
import pl.mateusz.kalksztejn.STM.services.Interfaces.TaskService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@EnableSwagger2
@RequestMapping("/tasks")
public class TaskController {
    TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //f
    @PostMapping
    public Task createTask(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("type") Type type,
            @RequestParam("status") Status status,
            @RequestParam("user_id") User userId) {
        return taskService.create(new Task(title, description, LocalDateTime.now(), type, status, userId));
    }

    //g
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAll();
    }

    //h
    @GetMapping("/TitleOrStatusOrType")
    public List<Task> getByTitleOrStatusOrType(String title, Type type, Status status) {
        return taskService.getByTitleOrStatusOrType(title, type, status);
    }

    //i
    @PutMapping("/ChangeStatus")
    public Task changeTaskStatus(
            @RequestParam("taskId") int taskId,
            @RequestParam("status") Status status
    ) {
        return taskService.changeStatus(taskId, status);
    }

    //j
    @DeleteMapping
    public boolean deleteTaskById(
            @RequestParam("taskId") int taskId
    ) {
        return taskService.deleteById(taskId);
    }


}
