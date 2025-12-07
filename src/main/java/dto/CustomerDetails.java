package dto;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
@Entity
@Table(name = "bank_customer_details")
public class CustomerDetails
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerid;
	@Column(name = "customer_name")
    private String customername;
	@Column(name = "customer_mobile_number")
    private Long customermobilenumber;
	@Column(name = "customer_addar")
    private Long aadharnumber;
	@Column(name = "customer_pan")
    private String pannumber;
	@Column(name = "customer_email")
    private String emailid;
	@Column(name = "customer_addres")
    private String address;
	@Column(name = "customer_designation")
    private String designation;
	@Column(name = "customer_accno")
    private Long accountnumber;
	@Column(name = "customer_pin")
    private Integer pin;
	@Column(name = "customer_ifsc_code")
    private String ifsccode;
	@Column(name = "customer_branch")
    private String branch;
	@Column(name = "customer_typeofacc")
    private String typeofaccount;
	@Column(name = "customer_savings")
    private Double amount;
	@Column(name = "customer_gender")
    private String gender;
    
	public CustomerDetails(){
		}
	public CustomerDetails(String customername, long customermobilenumber, long aadharnumber,
			String pannumber, String emailid, String address, String designation, long accountnumber, int pin,
			String ifsccode, String branch, String typeofaccount, double amount, String gender) {
		super();
//		this.customerid = customerid;
		this.customername = customername;
		this.customermobilenumber = customermobilenumber;
		this.aadharnumber = aadharnumber;
		this.pannumber = pannumber;
		this.emailid = emailid;
		this.address = address;
		this.designation = designation;
		this.accountnumber = accountnumber;
		this.pin = pin;
		this.ifsccode = ifsccode;
		this.branch = branch;
		this.typeofaccount = typeofaccount;
		this.amount = amount;
		this.gender = gender;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getPannumber() {
		return pannumber;
	}

	public void setPannumber(String pannumber) {
		this.pannumber = pannumber;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getTypeofaccount() {
		return typeofaccount;
	}

	public void setTypeofaccount(String typeofaccount) {
		this.typeofaccount = typeofaccount;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
    public Long getAccountnumber() { return accountnumber; }
    public void setAccountnumber(Long accountnumber) { this.accountnumber = accountnumber; }
    public Long getCustomermobilenumber() { return customermobilenumber; }
    public void setCustomermobilenumber(Long customermobilenumber) { this.customermobilenumber = customermobilenumber; }
    public Double getAmount() {return amount;}
    public void setAmount(double amount) {this.amount = amount;}
    public Long getAadharnumber() {return aadharnumber;}
	public void setAadharnumber(Long aadharnumber) {this.aadharnumber = aadharnumber;}
	public Integer getPin() {return pin;}
	public void setPin(int pin) {this.pin = pin;}
	@Override
	public String toString() {
		return "CustomerDetails [customerid=" + customerid + ", customername=" + customername
				+ ", customermobilenumber=" + customermobilenumber + ", aadharnumber=" + aadharnumber + ", pannumber="
				+ pannumber + ", emailid=" + emailid + ", address=" + address + ", designation=" + designation
				+ ", accountnumber=" + accountnumber + ", pin=" + pin + ", ifsccode=" + ifsccode + ", branch=" + branch
				+ ", typeofaccount=" + typeofaccount + ", amount=" + amount + ", gender=" + gender + "]";
	}
}

