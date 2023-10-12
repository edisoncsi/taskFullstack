package eco.com.spring.mcsv.tasks.services;

import eco.com.spring.mcsv.tasks.models.Task;

import java.util.List;
import java.util.Optional;

/**
 * @author edisoncsi on 11/10/23
 * @project mcsv-tasks
 */
public interface TaskService {

    List<Task> list();

    int getByStatus();

    boolean getTaskById(Long id);

    void insert(Task task);

    Optional<Task> updateTask(Long id, Task task);

    Optional<Task> updateDoneTask(Long id);

    void deleteTask(Long taskId);
}
