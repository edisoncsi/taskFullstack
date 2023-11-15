package eco.com.spring.mcsv.tasks.dtos;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author edisoncsi on 10/11/23
 * @project mcsv-tasks
 */

public class TaskDto {
    private long id;
    private String description;
    private Boolean done;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
