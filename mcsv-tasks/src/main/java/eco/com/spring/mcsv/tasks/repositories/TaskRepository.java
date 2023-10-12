package eco.com.spring.mcsv.tasks.repositories;

import eco.com.spring.mcsv.tasks.models.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author edisoncsi on 11/10/23
 * @project mcsv-tasks
 */
public interface TaskRepository extends CrudRepository <Task, Long>{

    @Query(value = "SELECT  count(*) FROM tasks u WHERE u.done = true ",nativeQuery = true)
    int findByStatus();


}
