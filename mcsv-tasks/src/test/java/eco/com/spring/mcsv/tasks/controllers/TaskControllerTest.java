package eco.com.spring.mcsv.tasks.controllers;


import eco.com.spring.mcsv.tasks.dtos.TaskDto;
import eco.com.spring.mcsv.tasks.models.Task;
import eco.com.spring.mcsv.tasks.services.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;


    @Test
    void testList() throws Exception {
        mockMvc.perform(get("/api"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testCreateTaskValidInput() {
    // Create a sample Task
    TaskDto sampleTask = new TaskDto();
        sampleTask.setId(22222);
        sampleTask.setDone(false);
        sampleTask.setDescription("Sample description");
    Task task = new Task();
    task.setId(sampleTask.getId());
    task.setDone(sampleTask.getDone());
    task.setDescription(sampleTask.getDescription());

    // Create a mock BindingResult without errors
    BindingResult mockBindingResult = mock(BindingResult.class);
    when(mockBindingResult.hasErrors()).thenReturn(false);

    // Call the createTask method with valid input
    ResponseEntity<?> responseEntity = taskController.createTask(sampleTask, mockBindingResult);

    // Verify that the response has a 201 Created status
    Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    // Verify that the taskService.insert method was called once
    verify(taskService, times(1)).insert(refEq(task) );
    }

    @Test
    public void testCreateTaskInvalidInput() {
        // Create a sample Task
        Task invalidTask = new Task();
        TaskDto sampleTask = new TaskDto();

        // Create a mock BindingResult with errors
        BindingResult mockBindingResult = mock(BindingResult.class);
        when(mockBindingResult.hasErrors()).thenReturn(true);

        // Call the createTask method with invalid input
        ResponseEntity<?> responseEntity = taskController.createTask(sampleTask, mockBindingResult);

        // Verify that the response has a 400 Bad Request status
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        // Verify that the taskService.insert method was not called
        verify(taskService, never()).insert(invalidTask);
    }

}
