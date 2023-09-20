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

# Technologies:
***
<div align="center">
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/192108890-200809d1-439c-4e23-90d3-b090cf9a4eea.png" alt="InteliJ" title="InteliJ" /></code>
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png" alt="Java" title="Java" /></code>
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/117201470-f6d56780-adec-11eb-8f7c-e70e376cfd07.png" alt="Spring" title="Spring" /></code>
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/183891303-41f257f8-6b3d-487c-aa56-c497b880d0fb.png" alt="Spring Boot" title="Spring Boot" /></code>
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/117207242-07d5a700-adf4-11eb-975e-be04e62b984b.png" alt="Maven" title="Maven" /></code>
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/117207493-49665200-adf4-11eb-808e-a9c0fcc2a0a0.png" alt="Hibernate" title="Hibernate" /></code>
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/190229463-87fa862f-ccf0-48da-8023-940d287df610.png" alt="Lombok" title="Lombok" /></code>
	<code><img height="50" src="https://user-images.githubusercontent.com/25181517/183896128-ec99105a-ec1a-4d85-b08b-1aa1620b2046.png" alt="MySQL" title="MySQL" /></code>
</div>

