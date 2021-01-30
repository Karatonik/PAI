package pl.mateusz.kalksztejn.STM.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.mateusz.kalksztejn.STM.models.User;
import pl.mateusz.kalksztejn.STM.services.Interfaces.TaskService;
import pl.mateusz.kalksztejn.STM.services.Interfaces.UserService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@EnableSwagger2
@RequestMapping("/users")
public class UserController {
    UserService userService;
    TaskService taskService;

    @Autowired
    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    //a
    @PostMapping
    public User createUser(
            @RequestParam("name") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        return userService.create(new User(name, lastName, email, password, false, LocalDateTime.now()));
    }

    //b
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    //c
    @GetMapping("/IdOrEmail")
    public List<User> getByEmail(Integer userId, String email) {
        return userService.getByEmailOrId(userId, email);
    }

    //d
    @PutMapping("/Activate")
    public boolean activateUser(
            @RequestParam("userId") int userId
    ) {
        return userService.activate(userId);
    }

    //e
    @DeleteMapping
    public boolean deleteUserById(
            @RequestParam("userId") int userId
    ) {
        return userService.deleteById(userId);
    }
}
