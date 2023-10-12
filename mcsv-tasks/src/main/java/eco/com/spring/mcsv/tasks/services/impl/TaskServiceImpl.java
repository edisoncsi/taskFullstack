package eco.com.spring.mcsv.tasks.services.impl;

import eco.com.spring.mcsv.tasks.models.Task;
import eco.com.spring.mcsv.tasks.repositories.TaskRepository;
import eco.com.spring.mcsv.tasks.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author edisoncsi on 11/10/23
 * @project mcsv-tasks
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Override
    public List<Task> list() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public int getByStatus() {
        return  taskRepository.findByStatus();
    }

    @Override
    public boolean getTaskById(Long id) {
        return taskRepository.findById(id).isPresent();
    }

    @Override
    public void insert(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Optional<Task> updateTask(Long id, Task task) {
        Optional<Task> taskFromDb = taskRepository.findById(id);

        if (taskFromDb.isPresent()) {
            Task _task = taskFromDb.get();
            _task.setId(id);
            _task.setDescription(task.getDescription());
            _task.setDone(task.getDone());
            taskRepository.save(_task);
        }
        return  taskFromDb;
    }

    @Override
    public Optional<Task> updateDoneTask(Long id) {
        Optional<Task> taskFromDb = taskRepository.findById(id);

        if (taskFromDb.isPresent()) {
            Task _task = taskFromDb.get();
            _task.setId(id);
            _task.setDescription(_task.getDescription());
            _task.setDone(!_task.getDone());
            taskRepository.save(_task);
        }
        return  taskFromDb;
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
