package eco.com.spring.mcsv.tasks.controllers;

import eco.com.spring.mcsv.tasks.dtos.TaskDto;
import eco.com.spring.mcsv.tasks.models.Task;
import eco.com.spring.mcsv.tasks.responses.BaseResponse;
import eco.com.spring.mcsv.tasks.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author edisoncsi on 11/10/23
 * @project mcsv-tasks
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:9000"})
public class TaskController {

    private final Logger logger = Logger.getLogger(TaskController.class.getName());

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(taskService.list());
    }

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskDto command, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(validationErrors(result));
        }

        try {
            Task task = new Task();
            task.setId(command.getId());
            task.setDescription(command.getDescription());
            task.setDone(command.getDone());
            taskService.insert(task);
            return new ResponseEntity<>(new BaseResponse("La tarea se ha creado exitosamente"), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return handleBadRequest(e);
        } catch (DataAccessException e) {
            return handleInternalError(e);
        }
    }

    @PutMapping({"/{taskId}"})
    public ResponseEntity<?> updateTask(@PathVariable("taskId") Long taskId, @Valid @RequestBody Task command, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(validationErrors(result));
        }

        try {
            Optional<Task> task = taskService.updateTask(taskId, command);
            if (task.isPresent())
                return new ResponseEntity<>(new BaseResponse("La tarea se actualizo exitosamente"), HttpStatus.OK);
            else
                return new ResponseEntity<>(new BaseResponse("La tarea no existe"), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return handleBadRequest(e);
        } catch (DataAccessException e) {
            return handleInternalError(e);
        }
    }

    @PostMapping({"/done/{taskId}"})
    public ResponseEntity<?> updateDoneTask(@PathVariable("taskId") Long taskId){

        try {
            Optional<Task> task = taskService.updateDoneTask(taskId);
            if (task.isPresent())
                return new ResponseEntity<>(new BaseResponse("La tarea se actualizo exitosamente"), HttpStatus.OK);
            else
                return new ResponseEntity<>(new BaseResponse("La tarea no existe"), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return handleBadRequest(e);
        } catch (DataAccessException e) {
            return handleInternalError(e);
        }
    }

    @DeleteMapping({"/{taskId}"})
    public ResponseEntity<?> deleteTodo(@PathVariable("taskId") Long taskId) {
        try {
            if (taskService.getTaskById(taskId)){
                taskService.deleteTask(taskId);
                return new ResponseEntity<>(new BaseResponse("La tarea se ha eliminado exitosamente"), HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(new BaseResponse("La tarea no existe"), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return handleBadRequest(e);
        } catch (DataAccessException e) {
            return handleInternalError(e);
        }

    }

    private ResponseEntity<?> handleBadRequest(Exception e) {
        logger.log(Level.WARNING,  String.format("Bad request: %s " , e.getMessage()));
        return ResponseEntity.badRequest().body(new BaseResponse(e.getMessage()));
    }

    private ResponseEntity<?> handleInternalError(Exception e) {
        logger.log(Level.SEVERE, String.format("Internal server error:  %s",  e.getMessage()), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse("Internal server error"));
    }

    private Map<String, String> validationErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
        return errors;
    }
}
