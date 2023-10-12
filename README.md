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


