# Toolify

Welcome to Toolify, a backend application developed in Eclipse, providing essential functionalities for managing addresses, products, user registration and login, purchases, and purchase history.

## Table of Contents

- [Introduction](#introduction)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Contributing](#contributing)

## Introduction

Toolify is a Java-based backend application designed to streamline the management of various entities such as addresses, products, user registration, purchases, and purchase history. The project is organized in a modular and structured manner, making it easy to understand and extend.

## Installation

Follow these steps to get Toolify up and running on your local machine:

### Prerequisites

1. **Java Development Kit (JDK):**
   - Ensure you have JDK 8 or later installed on your machine.

2. **Eclipse IDE:**
   - Download and install Eclipse IDE from [https://www.eclipse.org/downloads/](https://www.eclipse.org/downloads/).

3. **Git:**
   - Install Git on your machine. You can download Git from [https://git-scm.com/downloads](https://git-scm.com/downloads).

### Clone the Repository

```bash
git clone https://github.com/your-username/toolify.git
```

### Import Project into Eclipse

1. Open Eclipse IDE.

2. Click on `File` -> `Import`.

3. Select `Existing Maven Project` from the list.

4. Navigate to the cloned `toolify` directory and click `Finish`.

### Run the Application

1. Open the `ToolifyApplication.java` file located at `src/main/java/de.srh.toolify/`.

2. Right-click on the file and select `Run As` -> `Java Application`.

3. The application will start, and you should see the log indicating a successful startup.

## Usage

Toolify provides a set of controllers and services for managing different entities. Here are the main components:

- **Controllers:**
  - `AddressController.java`
  - `ProductController.java`
  - `PurchaseController.java`
  - `PurchaseHistoryController.java`
  - `UserLoginController.java`
  - `UserRegistrationController.java`
<br><br>
- **Entities:**
  - `AddressEntity.java`
  - `CategoryEntity.java`
  - `ProductEntity.java`
  - `PurchaseItemsEntity.java`
  - `PurchaseEntity.java`
  - `UserEntity.java`

## Project Structure

The project follows a well-organized structure for easy navigation:

```
toolify
│   .gitignore
│   README.md
│
└───src
    └───main
        ├───java
        │   └───de.srh.toolify
        │       │   ToolifyApplication.java
        │
        ├───resources
        │       application.properties
        │
        └───de.srh.toolify
            ├───config
            │       AppConfig.java
            │       SecurityConfig.java
            │
            ├───controllers
            │       AddressController.java
            │       ProductController.java
            │       PurchaseController.java
            │       PurchaseHistoryController.java
            │       UserLoginController.java
            │       UserRegistrationController.java
            │
            ├───dto
            │       LoginRequest.java
            │       PurchaseResponse.java
            │       ToolifyResponse.java
            │
            ├───entities
            │       AddressEntity.java
            │       CategoryEntity.java
            │       ProductEntity.java
            │       PurchaseItemsEntity.java
            │       PurchaseEntity.java
            │       UserEntity.java
            │
            ├───enums
            │       Roles.java
            │
            ├───exceptions
            │       AddressException.java
            │       AddressExceptionAdvice.java
            │       CustomDefaultExceptionResolver.java
            │       ProductException.java
            │       ProductExceptionAdvice.java
            │       PurchaseException.java
            │       PurchaseExceptionAdvice.java
            │       PurchaseItemsException.java
            │       UserException.java
            │       UserExceptionAdvice.java
            │
            ├───repositories
            │       AddressRepository.java
            │       CategoryRepository.java
            │       ProductRepository.java
            │       PurchaseItemsRepository.java
            │       PurchaseRepository.java
            │       UserLoginRepository.java
            │       UserRepository.java
            │
            ├───services
            │       AddressService.java
            │       ProductService.java
            │       PurchaseHistoryService.java
            │       PurchaseService.java
            │       UserDetailServiceImpl.java
            │       UserLoginService.java
            │       UserRegistrationService.java
            │
            └───validators
                    AddressPropsValidator.java
                    ProductPropsValidator.java
                    PurchaseItemsPropsValidator.java
                    PurchasePropsValidator.java
                    UserPropsValidator.java
                    ValidatorPropsValidator.java
```

## Configuration

The application is configured using the `application.properties` file in the `src/main/resources` directory. Customize the configuration parameters as needed.

## Contributing

Feel free to contribute to the development of Toolify by opening issues or submitting pull requests.

