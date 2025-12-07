package dto;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_details")
public class TransactionDetails 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trans_id")
    private int transactionid;
	@Column(name = "trans_type")
    private String transactiontype;
	@Column(name = "trans_date")
    private LocalDate transactiondate;
	@Column(name = "trans_time")
    private LocalTime transactiontime;
	@Column(name = "trans_amt")
    private double transactionamount;
	@Column(name = "trans_balance")
    private double balanceamount;
	@Column(name = "trans_accno")
    private long accountnumber;
    
    public TransactionDetails()
    {
    }
    	public TransactionDetails(int transactionid, String transactiontype, LocalDate transactiondate,
		LocalTime transactiontime, double transactionamount, double balanceamount, long accountnumber) {
		super();
		this.transactionid = transactionid;
		this.transactiontype = transactiontype;
		this.transactiondate = transactiondate;
		this.transactiontime = transactiontime;
		this.transactionamount = transactionamount;
		this.balanceamount = balanceamount;
		this.accountnumber = accountnumber;
	}
		public int getTransactionid() {
			return transactionid;
		}
		public void setTransactionid(int transactionid) {
			this.transactionid = transactionid;
		}
		public String getTransactiontype() {
			return transactiontype;
		}
		public void setTransactiontype(String transactiontype) {
			this.transactiontype = transactiontype;
		}
		public LocalDate getTransactiondate() {
			return transactiondate;
		}
		public void setTransactiondate(LocalDate transactiondate) {
			this.transactiondate = transactiondate;
		}
		public LocalTime getTransactiontime() {
			return transactiontime;
		}
		public void setTransactiontime(LocalTime transactiontime) {
			this.transactiontime = transactiontime;
		}
		public double getTransactionamount() {
			return transactionamount;
		}
		public void setTransactionamount(double transactionamount) {
			this.transactionamount = transactionamount;
		}
		public double getBalanceamount() {
			return balanceamount;
		}
		public void setBalanceamount(double balanceamount) {
			this.balanceamount = balanceamount;
		}
		public long getAccountnumber() {
			return accountnumber;
		}
		public void setAccountnumber(long accountnumber) {
			this.accountnumber = accountnumber;
		}
		@Override
		public String toString() {
			return "TransactionDetails [transactionid=" + transactionid + ", transactiontype=" + transactiontype
					+ ", transactiondate=" + transactiondate + ", transactiontime=" + transactiontime
					+ ", transactionamount=" + transactionamount + ", balanceamount=" + balanceamount
					+ ", accountnumber=" + accountnumber + "]";
		}
    
    
}

