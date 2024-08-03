# Project Name
Microservices-based e-commerce application.

# Project Description
The purpose of the project is to create a distributed application based on microservices which allows consumer users to purchase items published by other supplier users (business scheme known as "shopping cart").

## Getting Started
The project consists of 8 projects (4 libraries and 3 microservices) which are listed below:

* common-exceptions: Library to contain the exceptions used in the system
* common-entities: Library that centralizes entities mapped to data persistence.
* common-authentication: Library that encapsulates the classes related to the authentication of users who will use the developed services.
* common-definitions: Library dedicated to storing the other common definitions shared by the projects.

* ms-auth: Microservice focused on the authentication of users who consume the system's services
* ms-users: Microservice dedicated to encapsulating services related to users (user query, user registration and updating, etc.)
* ms-products: Microservice to contain product-related services (product registration, inventory maintenance, etc.). - PENDING
* ms-sales: Microservice dedicated to containing the services related to the sale of products. - PENDING

### Prerequisites
Once the libraries are compiled, the following projects have an embedded server which allows the initialization of the services without the need to install an additional server.
