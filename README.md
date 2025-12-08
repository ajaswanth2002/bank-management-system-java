# 🏦 Bank Management System (Java + Hibernate + MySQL)

A fully functional **Bank Management System** built using **Java**, **Hibernate ORM**, and **MySQL**.  
The project automatically creates database tables using Hibernate configuration and supports full banking operations such as deposit, withdrawal, fund transfer, and account management.

---

## 🚀 Features

### 👨‍💼 Customer Account Management
- Create new bank accounts
- Update customer information
- View account details

### 💳 Transaction Handling
- Deposit money
- Withdraw money
- Transfer funds between accounts
- View transaction history

### 🗄 Database & Hibernate
- Automatic table creation using Hibernate ORM
- Uses JPA annotations for mapping entities
- No manual SQL table creation required

---

## 🛠️ Tech Stack

| Technology | Description |
|-----------|-------------|
| **Java 8+** | Backend application |
| **Hibernate ORM** | Automatic table creation & mapping |
| **MySQL** | Database |
| **Maven** | Dependency management |
| **JPA annotations** | Entity configuration |

---

## 📂 Project Folder Structure
```bash
BankManagementSystemusingHibernet
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── dao
│   │   │   │   ├── AdminDAO.java
│   │   │   │   ├── CustomerDAO.java
│   │   │   │   └── TransactionDAO.java
│   │   │   ├── dto
│   │   │   │   ├── AdminDetails.java
│   │   │   │   ├── CustomerDetails.java
│   │   │   │   └── TransactionDetails.java
│   │   │   ├── exception
│   │   │   │   └── CustomerInvalidDataException.java
│   │   │   ├── main
│   │   │   │   ├── CreateDatabaseUsingHibernateConnection.java
│   │   │   │   └── Main.java
│   │   │   └── service
│   │   │       ├── AdminService.java
│   │   │       ├── CustomerService.java
│   │   │       └── TransactionService.java
│   │   └── resources
│   │       └── META-INF
│   │           └── persistence.xml
│   └── test
│       └── java
│           └── com
│               └── BankManagementSystemusingHibernet22
└── target
    ├── classes
    │   ├── dao
    │   ├── dto
    │   ├── exception
    │   ├── main
    │   ├── META-INF
    │   └── service
    ├── generated-sources
    ├── generated-test-sources
    ├── hibernate-demo-1.0.jar
    ├── maven-archiver
    ├── maven-status
    └── test-classes
---
```
update
## ⚙ Hibernate Configuration Example
| Option | Description |
|--------|------------|
| `create` | Drops & creates tables each run |
| `update` | Creates/updates tables without deleting data |
| `validate` | Only validates schema |
| `create-drop` | Creates tables & drops them on exit |

---

## ▶️ How to Run

### Step 1: Clone Repository
git clone https://github.com/your-username/bank-management-system.git
### Step 2: Configure MySQL Database (`hibernate.cfg.xml`)
```
jdbc:mysql://localhost:3306/bankdb
username: root
password: Jaswanth@1234
```
### **persistence.xml Settings**
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/bankdb"/>
<property name="javax.persistence.jdbc.user" value="root"/>
<property name="javax.persistence.jdbc.password" value="Jaswanth@1234"/>
<property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
### Step 3: Build & Run
mvn clean install
java -jar target/bank-management-system.jar
or simply run `Main.java` from your IDE.

---

📸 Demo Output
Welcome to Bank Management System
1. Admin Login
2. Customer Login
3. Exit
Enter Option:

---

## 🔮 Future Enhancements
- UI using Spring Boot + React
- Email alerts for transactions
- PDF statement generation
- JWT secured login

---

## ✨ Author

**Naga Venkata Jaswanth Adhikarla**

Java Developer | Passionate about Backend & Full-Stack Development
