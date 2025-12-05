package service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import dao.AdminDAO;
import dao.CustomerDAO;
import dto.AdminDetails;
import dto.CustomerDetails;

public class AdminService {
    Scanner s = new Scanner(System.in);
    AdminDAO adminDao = new AdminDAO();
    public static final String RED   = "\u001B[31m";
    public static final String BLUE  = "\u001B[34m";
    public static final String RESET = "\u001B[0m";
    public void adminlogin() throws Exception {
    	while(true) {
        System.out.println("Enter Email to login:");
        String email = s.next();
        System.out.println("Enter Pin to login:");
        int pin = s.nextInt();
        AdminDetails admin = adminDao.getAdminDetailsUsingEmailAndPin(email, pin);
        if (admin != null) {
            System.out.println("🎉 Welcome " + admin.getAdminName() +
                    (admin.getAdminGender().equalsIgnoreCase("Female") ? " Mam" :
                    	admin.getAdminGender().equalsIgnoreCase("Male") ? " Sir" : " Friend"));
            adminOperationsMenu();
            break;
        } else 
            System.out.println("Invalid Credentials!");
        }
    }

    public void adminOperationsMenu() throws Exception {
        while (true) {
        	System.out.println("Enter Your Option:\n" + " 1. View all customer details with serial numbers\n" +
    		        " 2. View all customers with pending values\n" +" 3. View all customers with no pending values\n" +
    		        " 4. Approve or Reject a customer account\n"+" 5. Exit Admin Menu");
            switch (s.nextInt()) {
                case 1:
                    viewAllCustomersWithSerial();
                    break;
                case 2:
                	viewPendingCustomers();
                    break;
                case 3:
                	viewNotPendingCustomers();
                	break;
                case 4:
                    approveOrRejectMenu();
                    break;
                case 5:
                	System.exit(0);
                    return;
                default:
                    System.out.println("Invalid Option! Choose 1-5.");
            }}}
    private String highlight(Object value) {
        if (value == null || String.valueOf(value).trim().isEmpty()
                || "null".equalsIgnoreCase(String.valueOf(value))) {
            return RED + "NULL" + RESET;   // Red for null
        }
        return BLUE + value.toString() + RESET; // Blue for valid values
    }
	private void printcustomerschooseformat(List<CustomerDetails> list) {
		System.out.println("Choose Format Style:");
	    System.out.println("1. Series Format");
	    System.out.println("2. Detailed List Format");
	    System.out.println("3. Table Format");
		int choice=s.nextInt();
		switch(choice) {
		case 1: seriesformatOfAllCustomers(list); break;
		case 2: listformatOfAllCustomers(list); break;
		case 3: tableformatOfAllCustomers(list);break;
		default: System.err.println("Invalid Choice Please Enter (1-3)"); 
		}
	}
    public void viewAllCustomersWithSerial() {
        List<CustomerDetails> list = adminDao.getCustomers();
        printcustomerschooseformat(list);
    }
    public void viewNotPendingCustomers() {
    	List<CustomerDetails>list = adminDao.getNotPendingCustomers();
    	printcustomerschooseformat(list);
    }
    public void viewPendingCustomers() {
        List<CustomerDetails> list = adminDao.getPendingCustomers();
        printcustomerschooseformat(list);
    }
	public void seriesformatOfAllCustomers(List<CustomerDetails> list) {
	    if(list.isEmpty()){
	        System.out.println("No customers.");
	        return;
	    }
	    int i=1;
	    for(CustomerDetails cd : list){
	    	System.out.println(
	    	        "CustomerDetails [customerid=" + highlight(String.valueOf(cd.getCustomerid())) + ", customername=" + highlight(String.valueOf(cd.getCustomername()))
	    			+ ", customermobilenumber=" + highlight(String.valueOf(cd.getCustomermobilenumber())) + ", aadharnumber=" + highlight(String.valueOf(cd.getAadharnumber())) + ", pannumber="
	    			+ highlight(String.valueOf(cd.getPannumber())) + ", emailid=" + highlight(String.valueOf(cd.getEmailid())) + ", address=" + highlight(String.valueOf(cd.getAddress())) + ", designation=" + highlight(String.valueOf(cd.getDesignation()))
	    			+ ", accountnumber=" + highlight(String.valueOf(cd.getAccountnumber())) + ", pin=" + highlight(String.valueOf(cd.getPin())) + ", ifsccode=" + highlight(String.valueOf(cd.getIfsccode())) + ", branch=" + highlight(String.valueOf(cd.getBranch()))
	    			+ ", typeofaccount=" + highlight(String.valueOf(cd.getTypeofaccount())) + ", amount=" + highlight(String.valueOf(cd.getAmount())) + ", gender=" + highlight(String.valueOf(cd.getBranch())) + "]");
	        i++;
	        System.out.println();
	    }
	}
	public void listformatOfAllCustomers(List<CustomerDetails> list) {
	    if(list.isEmpty()){
	        System.out.println("No customers.");
	        return;
	    }
	    int i=1;
	    for(CustomerDetails cd : list){
	        System.out.println("------------------------------------------------------------");
	        System.out.println("Name: " + highlight(cd.getCustomername()));
            System.out.println("Customer ID: " + highlight(String.valueOf(cd.getCustomerid())));
            System.out.println("Gender: " + highlight(cd.getGender()));
            System.out.println("Mobile Number: " + highlight(String.valueOf(cd.getCustomermobilenumber())));
            System.out.println("Email Id: " + highlight(cd.getEmailid()));
            System.out.println("Address: " + highlight(cd.getAddress()));
            System.out.println("Account Number: " + highlight(String.valueOf(cd.getAccountnumber())));
            System.out.println("Available Balance: " + highlight(String.valueOf(cd.getAmount())));
            System.out.println("Aadhar Card: " + highlight(String.valueOf(cd.getAadharnumber())));
            System.out.println("Bank IFSC Code: " + highlight(cd.getIfsccode()));
            System.out.println("Pan Card: " + highlight(cd.getPannumber()));
            System.out.println("Designation: " + highlight(cd.getDesignation()));
            System.out.println("Bank Pin: " + highlight(String.valueOf(cd.getPin())));
            System.out.println("Type of Account: " + highlight(cd.getTypeofaccount()));
            System.out.println("Bank Branch: " + highlight(cd.getBranch()));
	        i++;
	    }
	}
//	public void tableformatOfAllCustomers(List<CustomerDetails> list){
//	    if(list.isEmpty()){
//	        System.out.println("No customers.");
//	        return;
//	    }
//	    System.out.printf("%-5s  %-7s  %-11s  %-7s  %-9s  %-5s  %-45s  		%-5s 			 %-5s  			%-5s  			%-5s  			%-5s  		%-5s  		%-5s  		%-5s  		%-5s  \n",
////	    					S.No  ID    Name Gender Mobile Email  Address  Account Number  Available Balance  Aadhaar Number  Bank IFSC Code  Pan Card  Designation  Bank Branch  Bank Pin  Type of Account  
//	            "S.No","ID","Name","Gender","Mobile Number","Email Id","Address","Account Number","Available Balance","Aadhaar Number","Bank IFSC Code","Pan Card","Designation","Bank Branch","Bank Pin","Type of Account");
//	    int i = 1;
//	    for(CustomerDetails cd : list){
//	    System.out.printf("%-5d  %-3s  %-15s  %-8s  %-7s  %-10s 		%-45s  		%-5s  			%-5s  			%-5s  			%-5s  			%-5s  		%-5s  		%-5s  		%-5s  		%-5s \n",
////	    					S.No ID    Name   Gender Mobile Email    Address  Account Number  Available Balance  Aadhaar Number  Bank IFSC Code  Pan Card  Designation  Bank Branch  Bank Pin  Type of Account
//	                i,highlight(String.valueOf(cd.getCustomerid())),highlight(cd.getCustomername()),highlight(cd.getGender()), highlight(String.valueOf(cd.getCustomermobilenumber())),
//	                highlight(cd.getEmailid()), highlight(cd.getAddress()),highlight(String.valueOf(cd.getAccountnumber())),highlight(String.valueOf(cd.getAmount())),
//	                highlight(String.valueOf(cd.getAadharnumber())),highlight(cd.getIfsccode()),highlight(cd.getPannumber()),highlight(cd.getDesignation()),highlight(cd.getBranch()), highlight(String.valueOf(cd.getPin())), 
//	                highlight(cd.getTypeofaccount()));
//	        i++;
//	    }
//	}
//	public static String center(String text, int width) {
//	    if (text == null) text = "NULL";
//	    int padding = width - text.length();
//	    if (padding <= 0) return text; // If text is longer, return as is
//	    int padStart = padding / 2;
//	    int padEnd = padding - padStart;
//	    return " ".repeat(padStart) + text + " ".repeat(padEnd);
//	}
	public void tableformatOfAllCustomers(List<CustomerDetails> list){
	    if(list.isEmpty()){
	        System.out.println("No customers.");
	        return;
	    }
	    String line = "+-------+-----------------------------------------------------------------------------------------------------------------------------------------4---------------4------------------4---------------------4---------------4----------------4--------------4-------------------+";
	    System.out.println(line);
//	    System.out.printf("|%5s| %-12s| %-7s| %-14s| %-25s| %-18s| %-20s| %-21s| %-13s| %-16s| %-21s| %-13s| %-15s| %-12s| %-17s|\n",
//	            "S.No","Name","Gender","Mobile","Email","Address","Account Number","Balance","Aadhaar","IFSC Code","PAN","Designation","Branch","PIN","Account Type");
//	    System.out.printf("|%15s|%20s|%10s|%15s|%20s|%25s|%15s|%15s|%15s|%12s|%20s|%15s|%15s|%10s|%17s|\n",
//	            center("S.No",15), center("Name",20), center("Gender",10), center("Mobile",15),
//	            center("Email",20), center("Address",25), center("Account Number",15),
//	            center("Balance",15), center("Aadhaar",15), center("IFSC Code",12),
//	            center("PAN",20), center("Designation",15), center("Branch",15),
//	            center("PIN",10), center("Account Type",17));
//	    System.out.println(line);
//	    int i = 1;
//	    for (CustomerDetails cd : list) {
//	        System.out.printf("|%15s|%20s|%10s|%15s|%20s|%25s|%15s|%15s|%15s|%12s|%20s|%15s|%15s|%10s|%17s|\n",
//	                center(String.valueOf(i++),15),
//	                center(highlight(cd.getCustomername()),20),
//	                center(highlight(cd.getGender()),10),
//	                center(highlight(String.valueOf(cd.getCustomermobilenumber())),15),
//	                center(highlight(cd.getEmailid()),20),
//	                center(highlight(cd.getAddress()),25),
//	                center(highlight(String.valueOf(cd.getAccountnumber())),15),
//	                center(highlight(String.valueOf(cd.getAmount())),15),
//	                center(highlight(String.valueOf(cd.getAadharnumber())),15),
//	                center(highlight(cd.getIfsccode()),12),
//	                center(highlight(cd.getPannumber()),20),
//	                center(highlight(cd.getDesignation()),15),
//	                center(highlight(cd.getBranch()),15),
//	                center(highlight(String.valueOf(cd.getPin())),10),
//	                center(highlight(cd.getTypeofaccount()),17)
//	        );
//	    }
	    int i=1;
	    System.out.printf("|%5s| %-12s| %-7s| %-14s| %-25s| %-18s| %-20s| %-21s| %-13s| %-16s| %-21s| %-13s| %-15s| %-12s| %-17s|\n",
	            "S.No","Name","Gender","Mobile","Email","Address","Account Number","Balance","Aadhaar","IFSC Code","PAN","Designation","Branch","PIN","Account Type");
	    for(CustomerDetails cd : list){
	        System.out.printf("|%5s| %-17s| %-7s| %-14s| %-25s| %-18s| %-20s| %-21s| %-13s| %-16s| %-21s| %-13s| %-15s| %-12s| %-17s|\n",
	                i++,
	                highlight(cd.getCustomername()),
	                highlight(cd.getGender()),
	                highlight(String.valueOf(cd.getCustomermobilenumber())),
	                highlight(cd.getEmailid()),
	                highlight(cd.getAddress()),
	                highlight(String.valueOf(cd.getAccountnumber())),
	                highlight(String.valueOf(cd.getAmount())),
	                highlight(String.valueOf(cd.getAadharnumber())),
	                highlight(cd.getIfsccode()),
	                highlight(cd.getPannumber()),
	                highlight(cd.getDesignation()),
	                highlight(cd.getBranch()),
	                highlight(String.valueOf(cd.getPin())), 
	                highlight(cd.getTypeofaccount()));
	        System.out.println(line);
	    }
	}
    public void selectCustomerBySerial() {
        List<CustomerDetails> list = adminDao.getCustomers();
        if (list.isEmpty()) {
            System.out.println("⚠ No pending customers.");
            return;}
        System.out.println("Enter S.No:");
        int sno = s.nextInt();
        if (sno < 1 || sno > list.size()) {
            System.out.println("Invalid Serial Number!");
            return;}
        System.out.println("Selected Customer: " );
        }
	public void cleanNullValues(CustomerDetails cd) {
	    if (cd.getAccountnumber() == null) cd.setAccountnumber(0L);
	    if (cd.getCustomermobilenumber() == null) cd.setCustomermobilenumber(0L);
	    if (cd.getAadharnumber() == null) cd.setAadharnumber(0L);
	    if (cd.getAmount() == null) cd.setAmount(0.0);
	    if (cd.getPin() == null) cd.setPin(0);
	    if (cd.getEmailid() == null) cd.setEmailid("");
	    if (cd.getPannumber() == null) cd.setPannumber("");
	    if (cd.getAddress() == null) cd.setAddress("");
	    if (cd.getDesignation() == null) cd.setDesignation("");
	    if (cd.getIfsccode() == null) cd.setIfsccode("");
	    if (cd.getBranch() == null) cd.setBranch("");
	    if (cd.getTypeofaccount() == null) cd.setTypeofaccount("");
	    if (cd.getGender() == null) cd.setGender("");
	}
	public void approveOrRejectMenu() throws Exception {
	    List<CustomerDetails> list = adminDao.getPendingCustomers();
	    if (list.isEmpty()) {
	        System.out.println("No Pending Requests.");
	        return;}
	    int i = 1;
	    for (CustomerDetails cd : list) {
	        cleanNullValues(cd);  
	        System.out.print(i + ". Customer Name: " + cd.getCustomername() + "  Missing Values:");
	        List<String> missing = new ArrayList<>();
	        if (cd.getAccountnumber() == 0L || cd.getCustomermobilenumber() == null) missing.add("Account Number");
	        if (cd.getCustomermobilenumber() == 0L || cd.getCustomermobilenumber() == null) missing.add("Mobile Number");;
	        if (cd.getEmailid().isEmpty())missing.add("Email Id");;
	        if (cd.getAadharnumber() == 0L || cd.getCustomermobilenumber() == null) missing.add("Aadhaar number");
	        if (cd.getPannumber().isEmpty()) missing.add("PAN is empty");
	        if (cd.getAddress().isEmpty())missing.add("Address is empty");
	        if (cd.getDesignation().isEmpty()) missing.add("Designation is empty");
	        if (cd.getIfsccode().isEmpty()) missing.add("IFSC Code is empty");
	        if (cd.getBranch().isEmpty()) missing.add("Branch is empty");
	        if (cd.getTypeofaccount().isEmpty()) missing.add("Type of Account is empty");
	        if (cd.getAmount() == 0.0 || cd.getCustomermobilenumber() == null) missing.add("Savings is empty");
	        if (cd.getGender().isEmpty()) missing.add("Gender is empty");
	        if (cd.getPin() == 0 || cd.getCustomermobilenumber() == null) missing.add("PIN is empty");
	        if (missing.isEmpty())System.out.println("None");
	        else System.out.println(String.join(", ", missing));
	        i++;
	    }
	    System.out.println("\nEnter S.No from the above list to Approve/Reject:");
	    int sno = s.nextInt();
	    if (sno < 1 || sno > list.size()) {
	        System.out.println("Invalid Serial Number!");
	        return;}
	    CustomerDetails cd = list.get(sno - 1);
	    System.out.println("Enter Your Option:\n1. Approve Account\n2. Reject Account");
	    int choice = s.nextInt();
	    switch (choice) {
	        case 1:
	            approveAccount(cd);
	            break;
	        case 2:
	            rejectAccount(cd);
	            break;
	        default: System.out.println("Invalid Choice!");
	            }}
    public void approveAccount(CustomerDetails cd) {
        Random r = new Random();
        long accNo = 100000000000L + r.nextInt(999999999);
        int pin = 1000 + r.nextInt(9000);
        if (adminDao.acceptCustomer(cd.getCustomerid(), accNo, pin)) {
            System.out.println("Account Approved Successfully!");
            System.out.println("Assigned Account No: " + accNo);
            System.out.println("Assigned PIN: " + pin);
        } else 
            System.out.println("Server Error (500)");
        }
    public void rejectAccount(CustomerDetails cd) {
        if (adminDao.rejectAccount(cd.getCustomerid())) System.out.println("Account Rejected Successfully!");
        else System.out.println("Server Error (500)");
    }
}
