package pl.mateusz.kalksztejn.STM.services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.mateusz.kalksztejn.STM.models.Task;
import pl.mateusz.kalksztejn.STM.models.enums.Status;
import pl.mateusz.kalksztejn.STM.models.enums.Type;
import pl.mateusz.kalksztejn.STM.repositorys.TaskRepository;
import pl.mateusz.kalksztejn.STM.services.Interfaces.TaskService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
@Service
public class TaskServiceImp implements TaskService {
    TaskRepository taskRepository;

    @Autowired
    public TaskServiceImp(TaskRepository repository) {
        this.taskRepository = repository;
    }

    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll(Sort.by(Sort.Direction.DESC, "dateAdded"));
    }

    @Override
    public List<Task> getByTitleOrStatusOrType(String title, Type type, Status status) {
        return taskRepository.findByTitleOrStatusOrType(title, type, status);
    }

    @Override
    public Optional<Task> getById(int taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public boolean deleteById(int taskId) {
        AtomicBoolean isDeleted = new AtomicBoolean(false);
        getById(taskId).ifPresent(user -> {
            taskRepository.deleteById(taskId);
            isDeleted.set(true);
        });
        return isDeleted.get();
    }


    @Override
    public Task changeStatus(int taskId, Status status) {
        Task task = taskRepository.findByTaskId(taskId);
        task.setStatus(status);
        return taskRepository.save(task);
    }
}
