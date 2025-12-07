package dao;
import javax.persistence.*;
import dto.AdminDetails;
import dto.CustomerDetails;

import java.util.ArrayList;
import java.util.List;
import main.Main;

public class AdminDAO {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankManagementSystemusingHibernet");
    static EntityManager em = emf.createEntityManager();
	private static final String adminlogin ="SELECT ad FROM AdminDetails ad WHERE ad.adminEmail = ?1 AND ad.adminPin = ?2";
    private static final String get_cust_details_ifany_col_isnull ="SELECT cd FROM CustomerDetails cd WHERE "
    		+ "accountnumber IS NULL OR customername IS NULL OR customermobilenumber IS NULL OR emailid "
    		+ "IS NULL OR aadharnumber IS NULL OR pannumber IS NULL OR address IS NULL OR "
    		+ "designation IS NULL OR ifsccode IS NULL OR branch IS NULL OR "
    		+ "typeofaccount IS NULL OR amount IS NULL OR gender IS NULL OR pin IS NULL";
    private static final String get_cust_details_ifany_col_is_notnull ="SELECT cd FROM CustomerDetails cd WHERE " +"accountnumber IS NOT NULL "
    		+ "AND customername IS NOT NULL AND customermobilenumber IS NOT NULL AND emailid IS NOT NULL AND aadharnumber "
    		+ "IS NOT NULL AND pannumber IS NOT NULL AND address IS NOT NULL AND designation IS NOT NULL AND "
    		+ "ifsccode IS NOT NULL AND branch IS NOT NULL AND typeofaccount IS NOT NULL AND amount IS NOT "
    		+ "NULL AND gender IS NOT NULL AND pin IS NOT NULL";
    private static final String get_custdetailsaccnum="SELECT cd FROM CustomerDetails cd";
    private static final String updatepinaccountnumber ="UPDATE CustomerDetails cd SET accountnumber=?1, pin=?2 WHERE customerid=?3";
    private static final String rejectaccount ="DELETE FROM CustomerDetails WHERE customerid=?1";
	public AdminDetails getAdminDetailsUsingEmailAndPin(String email, int pin) {
	    try {
	        EntityManager em = Main.em;
	        Query query = em.createQuery(adminlogin); 
	        query.setParameter(1, email);
	        query.setParameter(2, pin);
//	        AdminDetails admin = (AdminDetails) query.getSingleResult();
//	        return admin;
	        List<AdminDetails> result = query.getResultList();
	        return result.isEmpty() ? null : result.get(0);
	
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Invalid email or pin");
	        return null;
	    }
	}
    public List<CustomerDetails> listofAllElements(String queries){
    	List<CustomerDetails> list = new ArrayList<>();
            EntityManager em = Main.em;
            Query query = em.createQuery(queries); 
            list = query.getResultList();           
        return list;
    }
    public List<CustomerDetails> getPendingCustomers() {
    	List<CustomerDetails> list=listofAllElements(get_cust_details_ifany_col_isnull);
        return list;
    }
    
    public List<CustomerDetails> getNotPendingCustomers(){
    	List<CustomerDetails> list=listofAllElements(get_cust_details_ifany_col_is_notnull);
        return list;
    }
    
    public List<CustomerDetails> getCustomers() {
        List<CustomerDetails> list = listofAllElements(get_custdetailsaccnum);
        return list;
    }
    public boolean acceptCustomer(int id, long accountnumber, int pin) {
            EntityManager em = Main.em;
            Query query = em.createQuery(updatepinaccountnumber); 
            em.getTransaction().begin(); 
            query.setParameter(1, accountnumber);
            query.setParameter(2, pin);
            query.setParameter(3, id);
            int r=query.executeUpdate();
            em.getTransaction().commit();
            return  r> 0;
        }
    public boolean rejectAccount(int id) {
            EntityManager em = Main.em;
            Query query = em.createQuery(rejectaccount);
            em.getTransaction().begin(); 
            query.setParameter(1, id);
            em.getTransaction().commit();
            return query.executeUpdate() > 0;
        }
}

