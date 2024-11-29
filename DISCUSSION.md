## Discussion of what was done in the submission

### Certification statement
I hereby certify that I alone worked on the requirements and instructions to build the solution for the backend test I 
was given. All the work presented in this project is solely my own effort, and I did not collaborate with or seek help
from anyone else during the development process.

I also certify that I have thoroughly completed the main requirements outlined in the test and, where applicable, 
addressed the bonus requirements as well.

I have approached this task with complete honesty and integrity, ensuring that all work is original and reflects my 
understanding and skills.

### Project Functionality Overview

### Web Service (Frontend)
1. **List all books:**
    - Displays a list of all books currently available in the catalog.
    - Fetches data from the Management Service using the JAX-RS Client.

2. **Add a new book:**
    - Provides a form to input book details (Name, ISBN, Publish Date, Price, Book Type).
    - Sends the book details to the Management Service API using the JAX-RS Client.

3. **Edit an existing book:**
    - Displays a form populated with the details of the selected book.
    - Updates the book information by calling the Management Service API via the JAX-RS Client.

4. **Delete a book:**
    - Deletes a selected book from the catalog by calling the delete endpoint of the Management Service API using the JAX-RS Client.

---

### Backend Service (Management Service)
1. **RESTful API to manage books:**
    - **List all books:**
        - HTTP GET `/books` endpoint.
        - Returns all books in the catalog as JSON.
    - **Add a new book:**
        - HTTP POST `/books` endpoint.
        - Accepts book details in JSON format and adds the book to the catalog.
    - **Update an existing book:**
        - HTTP PUT `/books}` endpoint.
        - Accepts updated book details in JSON format and updates the catalog entry.
    - **Delete a book:**
        - HTTP DELETE `/books/{isbn}` endpoint.
        - Deletes the specified book from the catalog.

2. **In-memory Database:**
    - Uses an in-memory database (e.g., H2) to store book details.
    - Ensures data persistence during the application's runtime.

---

### Integration with JAX-RS Client
- The Web Service (Frontend) uses the **JAX-RS Client** (javax.ws.rs.client.Client) to communicate with the Management Service (Backend).
This ensures seamless communication between the two services, enabling a functional catalog system.

### Additional Enhancements

### Frontend (Web Service)
1. **Bean Configuration for JAX-RS Client:** The JAX-RS Client is instantiated using a dedicated Spring Bean configuration.
ensuring code re-usability.
   


2. **Clean Code and Modularization:** Code logic is split into well-defined modules to separate concerns.


3. **Pagination:** Implemented pagination on the frontend to allow users to view book lists in chunks.
Allows navigation through pages, improving performance and user experience for large book collections.


4. **Docker Compose for Service Management:** The client service is containerized using Docker and managed with
   Docker Compose. This enables easy service running.

---

### Backend (Management Service)
1. **Docker Compose for Service Management:** The backend service is containerized using Docker and managed with 
Docker Compose. This enables easy service running.


2. **Input Validation:** Ensures proper validation for all required fields (`Name`, `ISBN`, `Publish Date`, `Price`, `Book Type`),
rejects invalid or missing inputs with appropriate error messages.


3. **Pagination:** Implemented pagination to allow the client service to request paginated results when getting all books.


4. **Uniform Response Format:** Ensured all API responses (successful or error) follow a uniform structure for consistency across the application,
improving client-side handling and debugging.


5. **Centralized Exception Handling:** Used `@RestControllerAdvice` to handle exceptions globally.
This provides a consistent and centralized approach to error handling across the API, using the appropriate HTTP
status codes.


6. **Audit Columns:** Added audit columns (`createdAt`, `updatedAt`) to track when a book’s details are created or modified.
Ensures transparency and traceability of changes made to the catalog.


7. **Actuator Endpoint for Health Checks:** Exposed a simple actuator endpoint (`/actuator/health`) to monitor the health of the application.
Useful for health checks, especially when running the application in a Docker container.


8. **Clean Code and Modularization:** Adhered to clean code principles, such as Meaningful class and method names. Separation of concerns with clear distinctions between **Controller**, **Service**, **Repository**, and **Model** layers.



9. **Unit Tests:** Added unit tests to cover key functionalities like CRUD operations for books and Exception handling.

---