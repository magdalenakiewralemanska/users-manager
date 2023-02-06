Users-manager project - product backlog:
TODO:
The task is to create an application for registration and management of registered users with the possibility of viewing the user's profile account and editing data. The project assumes options for different levels of access to the data contained in the database and different levels of access for users to individual functions. The project should include the possibility of blocking the user's account and deactivating it. In addition to typical personal data, the manager should also contain entries about the dates of registration and logging in for individual users. The basic functions that the application should perform are the registration of users to the database, their editing, deletion as well as logging in and logging out of a registered user. Additional functionalities included in the project should be the ability to add and edit the user's profile picture, recover the password via email, print the user's profile view and user table, as well as the ability to save data to a file.

BACKEND: 
Part 1 (10h) Create User and secure application (Spring security).
1.	Creating remote repositories and projects for backend and frontend. (1 h)
a)	Create project in IntelliJ for backend (Spring Boot) with all dependencies. (10 min.)
b)	Database configuration (MySQL). (15 min.)
c)	Add created project to the repository. (5 min.)
d)	Run project and test does it work. (5 min.)
e)	Create project in WebStorm for frontend (Angular) with all dependencies, npm installation, Angular CLI. (15 min.)
f)	Add created project to the repository. (5 min.)
g)	Run project and test does it work. (5 min.)
2.	Create model classes. (1h 10 min.)
a)	create User entity with all fields, getters, setter, constructors. (45 min.)
b)	create HttpResponse model with all fields, getters, setter, constructors. (25 min.)
3.	Create security configuration and classes for user login. (6h 30 min.)
a)	Create security classes for User to log in. (30 min.)
•	create CurrentUser class which implements UserDetails service. (20 min.)
•	create endpoint to check if the application is working properly - manual test. (10 min.)
b)	Create token provider. (1h 30 min.)
•	create method for generating token. (30 min.)
•	create verifier for token. (30 min.)
•	create method for getting authorities and for authentication. (30 min.)
c)	Create token filter. (40 min.)
d)	Create response for UI users when authentication went wrong. (1h)
•	create forbidden entry point in authentication. (30 min.)
•	create access denied handler for authentication. (30 min.)
e)	Create User repository. (10 min.)
f)	Implement User Details Service. (30 min.)
g)	Create security configuration class (1h 30 min.)
•	create bean for Authentication Manager. (20 min.)
•	create bean for Security Filter Chain. (40 min.)
•	create Password Encoder. (5 min.)
•	create testing endpoint to check if security is working properly - manual tests. (25 min.)
h)	Planning roles/authorities for different kind of users. Create proper components. (40 min.)
4.	Code clean up, refactoring, testing.(1h 20 min.)

Part 2 (10h) User registration and User login functions. CRUD operations implementation.
5.	Create registration for new user. (2h)
a)	Create validation for repeating email and username in registration process (40 min.)
b)	Create service with save method for registration. (40 min.)
c)	Add new endpoint for registration  - manual tests. (40 min.)
6.	Scheduling exceptions for application and adding them to the registration process. (2h)
a)	Username already exists exception. (20 min.)
b)	Email already exists exception. (20 min.)
c)	Create exception handler. (40 min.)
d)	Application manual tests. (40 min.)
7.	Create login functions for registered users with proper endpoint. (40 min.)
8.	Testing User log in. (40 min.)
9.	CRUD functions: create, read, update, delete methods.
a)	Create interface for CRUD methods. (20 min.)
b)	Create implementation class for interface (2h).
•	save method (40 min.)
•	update method (40 min.)
•	delete method (20 min.)
•	get all users (10 min.)
•	get user by id (10 min.)
c)	Create endpoints for CRUD methods. (1h)
10.	Testing application with postman, code cleaning, refactoring. (1h 20 min.)


FRONTEND
Part 3 (10h) Creating components and services for login. 
1.	Creating service for authentication. (3h)
a)	application configuration for communication with backend (on front and backend side) (1h)
b)	Create methods for authentication and user class that meets requirements from backend - authentication service. (2h)
•	create user class (30 min.)
•	create user login method (15 min.)
•	create register method (15 min.)
•	create log out method (15 min.)
•	create methods for token authentication and check login (30 min.)
•	code checking, refactoring (15 min.)
2.	Creating user service (1h).
a)	add methods for CRUD operations connecting with backend endpoints (1h)
•	method for getting all users (15 min.)
•	method for saving new user (15 min.)
•	method for updating new user (15 min.)
•	method for deleting user (15 min.)
3.	Adding interceptor for intercepting calls for registration and login endpoints (1h).
4.	Creating all components: for login, registration, user table, user profile with routing configuration. (30 min.)
5.	Creating login component methods, html, css. (3h 30 min.)
a)	Preparation HTML template with CSS for user login. (2h)
b)	Creating methods for login function corresponding with authentication service methods and html view. (30 min.)
c)	Creating appropriate notifications for users -notification service with module configuration. (1h)
6.	Testing application with backend, refactoring, code cleaning. (1h)

Part 4 (10h) Creating methods and views for registration component, creating users table.
7.	Creating registration component (methods, HTML, CSS). (2h)
a)	Preparation HTML template with CSS for user registration. (1h)
b)	Creating methods for registration function corresponding with authentication service methods and html view. (30 min.)
c)	Creating appropriate notifications . (30 min.)
8.	Testing registration, refactoring, code cleaning. (30 min.)
9.	 Creating table for displaying all users. (6h 30 min.)
a)	Creating HTML template header for application. (1h)
b)	Creating HTML template for users table. (2h)
c)	Creating methods for getting all users from database corresponding with user service methods and html view. (1h)
d)	Creating other methods for inputs/ buttons posted on html template (f.eg. searching method, user details view). (2h)
e)	Creating appropriate notifications for users. (30 min.)
10.	Testing application, code cleaning, refactoring. (1h)

Part 5 (10h) Creating components methods, HTML views with styling for CRUD methods (user profile view, add user, update user, delete user).
11.	Creating possibility to add new user for users with specified role (admin or moderator). (2h 30 min.)
a)	Creating HTML template for user addition - modal template. (1h)
b)	Creating methods for add new user corresponding with view. (1h)
c)	Creating appropriate notifications for users. (30 min.)
12.	Create update form (HTML) with appropriate methods for update users by user with specified role. (2h)
a)	Creating HTML template for user update - modal template. (30 min.)
b)	Creating method for update user. (1h)
c)	Creating appropriate notifications for users. (30 min.)
13.	Add delete method for users with specified role and notifications. (1h)
14.	Add user profile component, with possibility to update some data by logged user. (3h 30 min.)
a)	Creating HTML template for user profile view. (2h)
b)	Creating methods for update data for logged user. (1h)
c)	Add button logout with log out method. (30 min.)
15.	Testing all application functions, clean code, refactoring. (1h)

Part 6-7 (TODO if there's  enough time, TODO in the future):
1.	Creating possibility to add photo by user and change that photo.
2.	Creating password recovery option via email.
3.	Creating own "white label" page.
4.	Write tests for backend and frontend methods.
