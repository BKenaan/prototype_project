# PrototypeProject

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 18.2.8.
then i upgraded it version 19.0

### Prerequisetes
-Docker installed and running on ur system
-Port 4300 is vacant on ur machine
### How to Run the Project with Docker

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd prototype_project
2. Build Docker image:
  docker build -t angular-app .
3. Run docker container
  docker run -p 4300:4200 angular-app
4. Navigate to
  http://localhost:4300
  



## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.


## Running the Docker Container
 Build the Docker image:
   ```bash
   "docker build -t angular-app ."
   "docker run -p 4300:4200 angular-app"
   "acces application at http://localhost:4300"
##Run the app using:
"docker-compose up"
## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.
