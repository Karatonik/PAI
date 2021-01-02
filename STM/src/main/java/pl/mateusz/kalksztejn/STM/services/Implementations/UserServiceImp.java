package pl.mateusz.kalksztejn.STM.services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mateusz.kalksztejn.STM.models.Task;
import pl.mateusz.kalksztejn.STM.models.User;
import pl.mateusz.kalksztejn.STM.repositorys.TaskRepository;
import pl.mateusz.kalksztejn.STM.repositorys.UserRepository;
import pl.mateusz.kalksztejn.STM.services.Interfaces.UserService;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImp implements UserService {

    TaskRepository taskRepository;
    UserRepository userRepository;

    @Autowired
    public UserServiceImp(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getByEmailOrId(Integer userId, String email) {
        return userRepository.findByUserIdOrEmail(userId, email);
    }

    @Override
    public Optional<User> getById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public boolean activate(int userId) {
        Optional<User> userOptional = getById(userId);
        if (userOptional.isPresent()) {
            User userToActivate = userOptional.get();
            if (userToActivate.isStatus()) {
                userToActivate.setStatus(false);
            } else {
                userToActivate.setStatus(true);
            }
            userRepository.save(userToActivate);
        }
        return userOptional.get().isStatus();
    }

    @Override
    public boolean deleteById(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            for (Task task : taskRepository.findTasksByUserId(userId)) {
                taskRepository.delete(task);
            }
            userRepository.delete(optionalUser.get());
            return true;
        }
        return false;
    }
}
