
package main;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dto.AdminDetails;
import dto.CustomerDetails;
import service.AdminService;
import service.CustomerService;


public class Main {
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankManagementSystemusingHibernet");
	public static EntityManager em = emf.createEntityManager();
  public static void main(String[] args) throws Exception 
  {
//	  insertSampleData();
	  CustomerService customerservice=new CustomerService();
	  AdminService adminservice=new AdminService();
	   Scanner s=new Scanner(System.in);
	   System.out.println("Enter The Option \n 1.For Customer Registation "+"\n 2.For Customer Login"+"\n 3.For Admin Login");
	   switch(s.nextInt())
	   {
	   case 1:
		   System.out.println("Customer Registation");
		   customerservice.customerRegistration();
		   break;
	   case 2:
		   System.out.println("Customer Login");
		   customerservice.customerLogin();
		   break;
	   case 3:
	   System.out.println("Admin Login");
	   adminservice.adminlogin();
	   break;
	   default:
		   System.out.println("Invalid Request");
		   break;
	   }}
  public static void insertSampleData() {
	  em.getTransaction().begin();
	   // ================= ADMIN SAMPLE DATA ==================
       em.persist(new AdminDetails("Ravi Kumar", "Male", "ravi.admin@bank.com", 1234));
       em.persist(new AdminDetails("Priya Sharma", "Female", "priya.admin@bank.com", 4321));

       // ================= CUSTOMER SAMPLE DATA ==================
       em.persist(new CustomerDetails("Amit Verma", 9876543210L, 456789123456L, "AMITV1234L",
               "amitv@gmail.com", "Hyderabad", "Software Engineer", 1001002001L, 1111,
               "SBIN0001234", "Hyderabad", "Savings", 50000.00, "Male"));

       em.persist(new CustomerDetails("Sneha Reddy", 9876501234L, 456782345678L, "SNEHA2345Q",
               "sneha@gmail.com", "Bangalore", "Analyst", 1001002002L, 2222,
               "HDFC0005678", "Bangalore", "Savings", 75000.00, "Female"));       

       em.persist(new CustomerDetails("Rohan Singh", 9876123450L, 456789876543L, "ROHNS5678R",
    		   "rohan@gmail.com", "Mumbai", "Manager", 1001002003L, 3333,
    		   "ICIC0003456", "Mumbai", "Current", 120000.00, "Male"));

       em.persist(new CustomerDetails("Pooja Patel", 9898123451L, 456784567891L, "POOJP6789T",
    		   "pooja@gmail.com", "Chennai", "Accountant", 1001002004L, 4444,
    		   "KKBK0007654", "Chennai", "Savings", 30000.00, "Female"));

       em.persist(new CustomerDetails("Rahul Mehta", 9012345678L, 456783456789L, "RAHUM3456P",
    		   "rahul@gmail.com", "Pune", "Business Owner", 1001002005L, 5555,
    		   "SBIN0009876", "Pune", "Current", 200000.00, "Male"));

       em.persist(new CustomerDetails("Divya Sharma", 9123456780L, 456786543210L, "DIVYS1234H",
    		   "divya@gmail.com", "Delhi", "Teacher", 1001002006L, 6666,
    		   "HDFC0002456", "Delhi", "Savings", 45000.00, "Female"));

       em.persist(new CustomerDetails("Suresh Kumar", 9998887776L, 456785678901L, "SUERS4321Y",
    		   "suresh@gmail.com", "Hyderabad", "Clerk", 1001002007L, 7777,
    		   "SBIN0012345", "Hyderabad", "Savings", 15000.00, "Male"));

       em.persist(new CustomerDetails("Anjali Nair", 9876012345L, 456781234567L, "ANJLN7654W",
    		   "anjali@gmail.com", "Kochi", "Doctor", 1001002008L, 8888,
    		   "ICIC0001238", "Kochi", "Savings", 95000.00, "Female"));

       em.persist(new CustomerDetails("Vikram Rao", 9812345670L, 456788765432L, "VIKRM2345E",
    		   "vikram@gmail.com", "Visakhapatnam", "Engineer", 1001002009L, 9999,
    		   "ANDH0005673", "Vizag", "Savings", 82000.00, "Male"));

       em.persist(new CustomerDetails("Kavya Das", 9090909090L, 456789321456L, "KAVYD8765U",
    		   "kavya@gmail.com", "Kolkata", "Banker", 1001002010L, 1212,
    		   "UTIB0003789", "Kolkata", "Current", 67000.00, "Female"));
       em.getTransaction().commit();
       System.out.println("Sample Admins & Customers Inserted Successfully");
}
}

