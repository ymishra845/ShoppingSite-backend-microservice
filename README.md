Shopping Backend Microservices
This project implements a shopping backend service using a microservices architecture. The system consists of several modules:

**ShoppingBackendDataBaseApi:**

Responsible for managing the product database.
Uses MongoDB for product registration.
Utilizes PostgreSQL for other database operations.

**ShoppingBackendUserApi:**

Manages user registration and authentication.
FrontApi directs users to register as buyers, sellers, or admins.
UserApi verifies user validity and implements ACL in database API.

**ShoppingBackendFrontApi:**

Serves as the entry point for users to interact with the system.
Allows user registration, product browsing, buying, and selling.
Integrates with ShoppingBackendUserApi for user management.
Utilizes ShoppingBackendDataBaseApi for product-related operations.

**ShoppingBackendEmailApi:**

Sends emails for product registration and purchase notifications.
Integrated into the system for communication with buyers and sellers.


**Setup**

1. Clone the repository:
2. git clone https://github.com/your-username/shopping-backend-Service.git
cd shopping-backend
3.Install dependencies:

4.Configure databases:

Set up MongoDB for product registration.
Configure PostgreSQL for other database operations.

5.Run the microservices:
npm start


**Usage**

1.Access the FrontApi for user registration and interaction.
2.Register as a buyer, seller, or admin.
3.Use the FrontApi to buy, sell, and check products.
4.UserApi verifies user authenticity.
5.ShoppingBackendEmailApi sends email notifications for product-related activities.

**Contributions**
Contributions are welcome! If you find any issues or have improvements, please open an issue or submit a pull request.

**License**
This project is licensed under the MIT License - see the LICENSE file for details.


