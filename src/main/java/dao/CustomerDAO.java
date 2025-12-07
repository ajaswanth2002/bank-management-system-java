package dao;
import dto.CustomerDetails;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import main.Main;

public class CustomerDAO {
	private static final String selectLogin="SELECT cd FROM CustomerDetails cd WHERE (cd.customermobilenumber = ?1 OR cd.emailid = ?2) AND cd.pin = ?3";
	private static final String accNumQuery ="SELECT cd FROM CustomerDetails cd WHERE accountnumber = ?1";
	private static final String emailexits="SELECT COUNT(cd) FROM CustomerDetails cd WHERE cd.emailid = ?1";
	private static final String moblieexits="SELECT COUNT(cd) FROM CustomerDetails cd WHERE cd.customermobilenumber = ?1";
	Scanner s=new Scanner(System.in);
    public boolean insertCustomerDetails(CustomerDetails customer) {
        EntityManager em = Main.em;
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
    public boolean emailExists(String email) {
        EntityManager em = Main.em;
        try {
            Query query = em.createQuery(emailexits);
            query.setParameter(1, email);
            Long count = (Long) query.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean mobileExists(Long mobile) {
        EntityManager em = Main.em;
        try {
            Long count = em.createQuery(moblieexits, Long.class).setParameter(1, mobile).getSingleResult();
            return count > 0;
        } catch (Exception e) {
            return false;
        }
    }
	public CustomerDetails getCustomerByLogin(String emailOrMobile, Integer pin) {
	    EntityManager em = Main.em;
	    try {
	        Query query = em.createQuery(selectLogin, CustomerDetails.class);
	        query.setParameter(1, safeConvertToLong(emailOrMobile));
	        query.setParameter(2, emailOrMobile);
	        query.setParameter(3, pin);
	        return (CustomerDetails) query.getSingleResult();
	    } catch (Exception e) {
	        return null; 
	    }}
    	private Long safeConvertToLong(String value) {
    	    try { return Long.parseLong(value); }
    	    catch (Exception e) { return -1L; }
    	}
    public CustomerDetails getCutomerDetailsusingAccnum(long accnum) throws Exception {
    	try {
            EntityManager em = Main.em;
            Query query = em.createQuery(accNumQuery);
            query.setParameter(1, accnum);
            List<CustomerDetails> list = query.getResultList();
            if (!list.isEmpty()) return list.get(0);
            else return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}



