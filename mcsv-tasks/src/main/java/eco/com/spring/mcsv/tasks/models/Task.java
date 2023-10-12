package eco.com.spring.mcsv.tasks.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author edisoncsi on 11/10/23
 * @project mcsv-tasks
 */

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    private long id;
    @NotNull
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
