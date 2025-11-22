---

# 📌 **README for Bank Management System (Java + JDBC)**  
### (Place in: `/bank-management-system-java/README.md`)

```md
# Bank Management System — Java + JDBC

A complete **Java-based banking application** built using **Core Java, JDBC, and MySQL**, featuring customer and admin modules, secure login, account operations, and transaction management.

---

## 🚀 Features

### 👤 Customer Module
- Customer Registration
- Customer Login
- Check Account Details
- View Balance
- Perform Transactions (Credit/Debit)

### 🛡️ Admin Module
- Admin Login
- View All Customers
- View All Transactions
- Search Customer by Account Number

### 💾 Database Support
- MySQL database for customer, admin, and transaction records.
- Uses JDBC PreparedStatements for secure database operations.

---

## 🛠️ Tech Stack

- **Java (Core Java, OOPs)**
- **JDBC**
- **MySQL**
- **Eclipse / IntelliJ**

---

## 📦 Folder Structure
src/
└── com.bank
├── dao/
│    ├── CustomerDAO.java
│    ├── AdminDAO.java
│    └── TransactionDAO.java
├── dto/
│    ├── CustomerDetails.java
│    ├── AdminDetails.java
│    └── TransactionDetails.java
├── service/
│    ├── CustomerService.java
│    ├── AdminService.java
│    └── TransactionService.java
├── exception/
│    └── CustomerInvalidDataException.java
└── main/
└── Main.java

---

## ⚙️ How to Run

### 1️⃣ Import the project in Eclipse / IntelliJ  
### 2️⃣ Configure MySQL Database
Create database:
```sql
CREATE DATABASE bank_system;
Import tables using your SQL script.

3️⃣ Update MySQL credentials in DAO files:
DriverManager.getConnection(
    "jdbc:mysql://127.0.0.1:3306/bank_system",
    "root",
    "newpassword"
);
4️⃣ Run Main Class:
Main.java
