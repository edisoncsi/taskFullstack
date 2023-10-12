# taskFullstack
Task management

## Import the project Backend (mcsv-tasks) into Intellij

  1. Download the project as zip file into your computer
  2. Unzip the project at your desired location
  3. Open the project into intellij

## Import the project Frontend (mcsv-tasks-front) into Vscode

  1. Download the project as zip file into your computer
  2. Unzip the project at your desired location
  3. In a terminal and inside the folder we write: vscode .
  4. Open the project into Vscode

## Run Project
  1. Firts run McsvTasksApplication the backend project.
  2. Second in a terminal execute "npm install" then "npm start" and the run this frontend project.

## Test API in Postman

### GET  
    curl --location 'localhost:8080/api'
### POST 
    
    curl --location 'localhost:8080/api' \
    --header 'Content-Type: application/json' \
    --data '{
        "id": 8888888,
        "description": "Desarrollar prueba4",
        "done": false
    }'

### PUT 
    
    curl --location --request PUT 'localhost:8080/api/66666666' \
    --header 'Content-Type: application/json' \
    --data '{
        "id": 8888888,
        "description": "Actualizar tarea 2",
        "done": true
    }'
### DELETE 
    
    curl --location --request DELETE 'localhost:8080/api/8888888' \
    --data ''
    
## Admin Database H2

  * Run project
  * Open browser
     * http://localhost:8080/h2-ui
       
## SWAGGER API

### localhost:8080/swagger-ui/index.html 

# FRONT USER MANUAL

  1 To create a task you only have to enter the text and then with Enter or the Add Button it is added.
  
  2 To edit we click on the Edit button and the box is enabled, we save with Enter
  
  3 To delete we click on the red button
  
  4 To update a task we click on the radiobutton and the status is updated.

# Result 

![Diagram](./backendDevTest/assets/frontTask.jpg "Result")
