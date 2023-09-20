# Users Manager project - backend part:

It is a simple application for registering and managing registered users with the ability to view the user's profile account and edit data. The project assumes the possibility of different levels of access to the data contained in the database and different levels of user access to individual functions. The project includes the possibility of blocking and deactivating a user account. In addition to typical personal data, the application also stores entries regarding registration and login dates for individual users. 
This application consists of two basic parts: frontend and backend. This repository contains the backend part written in Java using the Spring framework. The basic functions that the application performs are: 
1. user registration in the database,
2. editing user data, 
3. deleting a user, 
4. logging in and logging out of a registered user. 
Additional functionalities included in the project include printing the user profile view and user table, as well as the ability to save data to a file.

# Backend part:

The backend part of the application consists primarily of creating a user entity, creating the ability to log in the user based on Spring security and JWT Token, along with assigning roles to individual users, including the administrator. The user has the option of registering his account. The application supports basic CRUD methods (create, read, update, delete) for the user. Individual options for editing basic data and deleting data have been divided according to the roles assigned to users.

# In the future:

In the future, it is also planned to create such functionalities as:
1.	Creating possibility to add photo by user and change that photo.
2.	Creating password recovery option via email.
