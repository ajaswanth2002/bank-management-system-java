package service;
import dao.CustomerDAO;
import dto.CustomerDetails;
import dto.TransactionDetails;
import exception.CustomerInvalidDataException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import main.Main;

public class CustomerService{
	CustomerDAO customerDAO=new CustomerDAO();
	TransactionDetails transactiondetails = new TransactionDetails();
	TransactionService transactionService=new TransactionService();
	Scanner s=new Scanner(System.in);
	private final String pinupdate = "UPDATE CustomerDetails SET customer_pin = ? WHERE customer_accno = ?1";
	public void customerRegistration() throws Exception {
	    Scanner s = new Scanner(System.in);
	    CustomerDAO customerDAO = new CustomerDAO();
	    System.out.println("Enter Customer Name:");
	    String name = s.next();
	    for (char ch : name.toCharArray()) {
	        if (Character.isDigit(ch) || name.length() < 4)
	            throw new CustomerInvalidDataException("Invalid Name: No digits allowed, minimum 4 characters");
	    }
	    System.out.println("Enter Email:");
	    String email = s.next();
	    while (true) {
	        boolean isGmail = email.endsWith("@gmail.com");
	        boolean hasDigit = email.matches(".*\\d.*");
	        if (!isGmail || !hasDigit) {
	            System.err.println("Invalid Email! Must end with @gmail.com and contain at least one digit:");
	            email = s.next();
	            continue;}
	        if (customerDAO.emailExists(email)) {
	            System.err.println("Email already exists! Enter another:");
	            email = s.next();
	            continue;
	        }
	        break;
	    }
	    System.out.println("Enter Mobile Number:");
	    Long mobile = s.nextLong();
	    while (true) {
	        if (!(mobile >= 6000000000L && mobile <= 9999999999L)) {
	            System.err.println("Invalid Mobile Number! Enter again:");
	            mobile = s.nextLong();
	            continue;}
	        if (customerDAO.mobileExists(mobile)) {
	            System.err.println("Mobile number already exists! Enter another:");
	            mobile = s.nextLong();
	            continue;}
	        break;
	    }
	    System.out.println("Enter Aadhar Number:");
	    Long aadhar = s.nextLong();
	    if (aadhar < 123456789012L)
	        throw new CustomerInvalidDataException("Invalid Aadhar Number");
	    String pan;
	    while (true) {
	        System.out.println("Enter Customer PAN Number (e.g., ABCDE1234F):");
	        pan = s.next();
	        if (pan.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) break;
	        System.err.println("Invalid PAN format!");
	    }
	    System.out.println("Enter Address:");
	    s.nextLine();
	    String address = s.nextLine();
	    System.out.println("Enter Designation:");
	    String designation = s.nextLine();
	    System.out.println("Enter Gender (Male/Female/Others):");
	    String gender = s.next();
	    if (!gender.matches("(?i)male|female|others"))
	        throw new CustomerInvalidDataException("Invalid Gender");
	    System.out.println("Enter Account Type: 1.Savings  2.Current");
	    int type = s.nextInt();
	    String typeOfAccount;
	    if (type == 1) typeOfAccount = "Savings";
	    else if (type == 2) typeOfAccount = "Current";
	    else  throw new CustomerInvalidDataException("Invalid Account Type");
	    System.out.println("Enter Opening Amount:");
	    double amount = s.nextDouble();
	    System.out.println("Enter PIN:");
	    int pin = s.nextInt();
	    System.out.println("Enter Branch:");
	    String branch = s.next();
	    System.out.println("Enter IFSC Code:");
	    String ifsc = s.next();
	    Long accountNumber = 1000000000L + (long)(Math.random() * 9000000000L);
	    CustomerDetails cust = new CustomerDetails();
	    cust.setCustomername(name);
	    cust.setEmailid(email);
	    cust.setCustomermobilenumber(mobile);
	    cust.setAadharnumber(aadhar);
	    cust.setPannumber(pan);
	    cust.setAddress(address);
	    cust.setDesignation(designation);
	    cust.setGender(gender);
	    cust.setTypeofaccount(typeOfAccount);
	    cust.setAmount(amount);
	    cust.setPin(pin);
	    cust.setBranch(branch);
	    cust.setIfsccode(ifsc);
	    cust.setAccountnumber(accountNumber);
	    if (customerDAO.insertCustomerDetails(cust)) {
	        System.out.println("ðŸŽ‰ Registration Successful!");
	        System.out.println("Your Account Number: " + accountNumber);
	    } else System.out.println("Registration Failed");
	}
	public void customerLogin() throws Exception {
	    Scanner s = new Scanner(System.in);
	    while (true) {
	        System.out.println("Enter customer emailid or mobile number");
	        String emailOrMobile = s.next();
	        System.out.println("Enter customer pin");
	        Integer pin = s.nextInt();
	        CustomerDetails customer = customerDAO.getCustomerByLogin(emailOrMobile, pin);
	        if (customer != null) {
	        	 System.out.println("🎉 Welcome " + customer.getCustomername() +
	                     (customer.getGender().equalsIgnoreCase("Female") ? " Mam" :
	                    	 customer.getGender().equalsIgnoreCase("Male") ? " Sir" : " Friend"));
	            break;
	        } else System.err.println("Invalid Email/Mobile Number or PIN!\n");
	    }
	    customeroperations();
	    s.close();
	}
   public void customeroperations() throws Exception {
	   System.out.println("Enter The Option \n 1.For Credit "+"\n 2.For Debit "+"\n 3.For Check Balance "+ "\n 4.For Statement "+"\n 5.For Change Pin "+"\n 6.For Close Account ");
	   switch(s.nextInt()){
	   case 1: System.out.println("To Credit the Amount");
	   		credit();
		   break;
	   case 2: System.out.println("To Debit the Amount");
	   		debit();
	   		break;
	   case 3: System.out.println("To Check Balance");
	   		checkbalance();
	   		break;
	   case 4: System.out.println("To get Bank Statement");
	   		statement();
	   		break;
	   case 5: System.out.println("To Change Pin");
  		changepin();
  		break;
	   case 6: System.out.println("To Close this Account");
	   		closeAccount();
	   		break;
	   default: System.out.println("Invaild Choice");
	}
   }
   public CustomerDetails checkAccNumber() throws Exception {
	    while (true) {
	        System.out.print("Enter Account Number: ");
	        long accnum = s.nextLong();
	        CustomerDetails customer = customerDAO.getCutomerDetailsusingAccnum(accnum);
	        if (customer == null) 
	            System.err.println("Invalid Account Number! Try again...");
	        else return customer;}
	}
	public void credit() throws Exception {
	    CustomerDetails customer = checkAccNumber();  // Get customer entity
	    long accnum = customer.getAccountnumber();
	    System.out.println("Account found: " + customer.getCustomername());
	    System.out.println("Available Balance: " + customer.getAmount());
	    System.out.println("Enter amount to Credit:");
	    double creditamt = s.nextDouble();
	    try {
	        EntityManager em = Main.em;
	        em.getTransaction().begin(); 
	        double newBalance = customer.getAmount() + creditamt;
	        customer.setAmount(newBalance);
	        em.merge(customer);
	        em.getTransaction().commit();
	        TransactionDetails t = new TransactionDetails();
	        t.setTransactiontype("Credit");
	        t.setTransactionamount(creditamt);
	        t.setBalanceamount(newBalance);
	        t.setTransactiondate(LocalDate.now());
	        t.setTransactiontime(LocalTime.now());
	        t.setAccountnumber(accnum);
	        transactionService.inserttransactionDetails(t);
	        System.out.println("Amount Credited Successfully!");
	        System.out.println("New Balance: " + newBalance);
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    closeAccount();
	}
   public void debit() throws Exception {
	    CustomerDetails customer = checkAccNumber();
		 long accnum = customer.getAccountnumber();
	    System.out.println("Account found: " + customer.getCustomername());
	    System.out.println("Available Balance: " + customer.getAmount());
	    System.out.println("Enter amount to debit:");
	    double debitamt = s.nextDouble();
	    if (debitamt > customer.getAmount()) {
	        System.err.println("Insufficient Balance");
	        return;}
	    try {
	        EntityManager em = Main.em;
	        em.getTransaction().begin(); 
	        double newBalance = customer.getAmount() - debitamt;
	        customer.setAmount(newBalance);
	        em.merge(customer);
	        em.getTransaction().commit();
	        TransactionDetails t = new TransactionDetails();
	        t.setTransactiontype("Debit");
	        t.setTransactionamount(debitamt);
	        t.setBalanceamount(newBalance);
	        t.setTransactiondate(LocalDate.now());
	        t.setTransactiontime(LocalTime.now());
	        t.setAccountnumber(accnum);
	        transactionService.inserttransactionDetails(t);
	        System.out.println("Amount Debited Successfully!");
	        System.out.println("New Balance: " + newBalance);
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    closeAccount();
	    }
   public void checkbalance() throws Exception {
	// TODO Auto-generated method stub
	   CustomerDetails customer =checkAccNumber();
	   System.out.println("Account found: " + customer.getCustomername());
	    System.out.println("Available Balance: " + customer.getAmount());
	    System.out.println("Do you want to credit / debit amount from your account \n Enter Yes/No:");
	    String debitorcredit=s.next();
		if(debitorcredit.equalsIgnoreCase("yes")) {
			System.out.println("Please Enter the number \n 1.For Credit \n 2.For Debit");
	    int debitorcreditchoice=s.nextInt();
	    	switch (debitorcreditchoice) {
			case 1:
				System.out.println("To Credit the Amount");
				credit();
			case 2:
				System.out.println("To Debit the Amount");
				debit();
			default:
				System.out.println("Invalid account");
			}}
		else if(debitorcredit.equalsIgnoreCase("no"))
			customeroperations();
		}
   public void changepin() throws Exception {
	    CustomerDetails customer = checkAccNumber();
	    System.out.println("Account found: " + customer.getCustomername());
	    System.out.println("Please Enter your Mobile Number to change");
	    long mobile_num = s.nextLong();
	    customer.getCustomermobilenumber();
	    while (mobile_num != customer.getCustomermobilenumber()) {
	        System.out.println("Incorrect Mobile Number. Try again:");
	        s.nextLine();
	        mobile_num = s.nextLong();
	    }
	    System.out.print("Please Enter your new Pin: ");
	    int newpin = s.nextInt();
	    try {
            EntityManager em = Main.em;
            em.getTransaction().begin(); 
	        customer.setPin(newpin);
	        em.getTransaction().commit();
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    System.out.println("Pin Changed Successfully!");
	    closeAccount();
	}
   public void closeAccount() throws Exception {
	    CustomerDetails customer = checkAccNumber();
	    System.out.println("Account found: " + customer.getCustomername());
	    System.out.println("Are you sure you want to close this account? (yes/no)");
	    String confirm = s.next();
	    if(confirm.equalsIgnoreCase("yes")) {
	    	System.out.println("Account closed successfully.");
	    	System.exit(0);}
	    else if(confirm.equalsIgnoreCase("no")) {
	    	System.out.println("Failed to close account.");
	    	System.out.println("Getting to:"+customer.getCustomername()+" Bank Account");
	    	customeroperations();}
   }
   public void statement() throws Exception{
	   CustomerDetails customer =checkAccNumber();
	   long accnum = customer.getAccountnumber();
       List<TransactionDetails> list = transactionService.transactionDAO.getAllTransactionByAccountNumber(accnum);
       if (list.isEmpty()){
           System.out.println("No Transactions Found!");
           return;
       }
       System.out.println("------------------------------------------------------------");
       System.out.println("                 ACCOUNT STATEMENT");
       System.out.println("------------------------------------------------------------");
       System.out.println("Type\tAmount\tBalance\tDate\t\tTime");
       System.out.println("------------------------------------------------------------");
       for (TransactionDetails t : list){
           System.out.println(
                   t.getTransactiontype() + "\t" +
                   t.getTransactionamount() + "\t" +
                   t.getBalanceamount() + "\t" +
                   t.getTransactiondate() + "\t" +
                   t.getTransactiontime()
           );
       }
       System.out.println("------------------------------------------------------------");
       closeAccount();
   }
}

