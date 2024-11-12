# StoreManagementTool

This is a Java Spring Boot application with PosgreSQL database that is designed to keep evidence of the products from a store. It contains functionalities that cand be used by normal or admin users. All endpoints were tested using Postman.

## Architecture
- **Authorization and access control** - with token and a specific role attributed to a user
- **Entities and Repositories** - for products, users and used JPA to communicate with PostgreSQL database
- **Services** - implementing the business logic
- **Controllers** - create endpoints

## If there are some errors, there will be thrown suggestive exceptios
## There were implemented **JUnit** and **Mockito** tests for the methods used to manipulate the products logic

## The most important features are:
**Register, SignUp and SignIn** - With spring Security, it contains the posibility to register a new user with a specific role; there are also multiple conditions that have to be met for the user to be added into database (password with at least 6 letters, unique email and username). Based on the token received and the role attributed, it will be used to make certain endpoints.
**Management of the products**:
- GET /api/products/{id} product by id - display informations, such as name, description, quantity, price and also its availability about a product with a certain id
- POST /api/products (create) a product and save it into database ONLY if the user which is currently logged in is ADMIN
- DELETE /api/products/{id} - delete a product from the databse based on its id, ONLY if the user that is currently logged in is ADMIN
- GET /api/products - get all the products saved into the database, filtered by their current availability
- PUT /api/products/{id} - update a product based on its name, quantity, availability or price (being able to make multiple changes)
- PATCH /api/products/changePrice/{id} - update only the price of a product with a certain id. If price is negative throw specific error
- PATCH /api/products/changeQuantity/{id} - update only the quantity of a product based on its id. If the updated quantity is negative, throw specific error 
