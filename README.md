Users-manager project - backend part:

It is a simple application for registering and managing registered users with the ability to view the user's profile account and edit data. The project assumes the possibility of different levels of access to the data contained in the database and different levels of user access to individual functions. The project includes the possibility of blocking and deactivating a user account. In addition to typical personal data, the application also stores entries regarding registration and login dates for individual users. 
This application consists of two basic parts: frontend and backend. This repository contains the backend part written in Java using the Spring framework. The basic functions that the application performs are: 1. user registration in the database,
2. editing user data, 
3. deleting a user, 
4. logging in and logging out of a registered user. 
Additional functionalities included in the project include printing the user profile view and user table, as well as the ability to save data to a file.

The backend part of the application consists primarily of creating a user entity, creating the ability to log in the user based on Spring security and JWT Token, along with assigning roles to individual users, including the administrator. The user has the option of registering his account. The application supports basic CRUD methods (create, read, update, delete) for the user. Individual options for editing basic data and deleting data have been divided according to the roles assigned to users.

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
