package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import main.Main;
import dto.TransactionDetails;

public class TransactionDAO {
    private String transactiondata = "SELECT td FROM TransactionDetails td WHERE trans_accno=?1 ORDER BY trans_date, trans_time";
    public boolean insertTransationvalues(TransactionDetails transdetails) {
    	try {
            EntityManager em = Main.em;
            em.getTransaction().begin(); 
            em.persist(transdetails);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

public List<TransactionDetails> getAllTransactionByAccountNumber(long accountNumber) {
    List<TransactionDetails> list = new ArrayList<>();

    try {
        EntityManager em = Main.em;
        Query query = em.createQuery(transactiondata);
        query.setParameter(1, accountNumber);
        list = query.getResultList();
        for (TransactionDetails td : list)
            System.out.println(td.getTransactionid() + " | " +td.getTransactiontype() + " | " +td.getTransactiondate() + 
            		" | " +td.getTransactiontime() + " | " +td.getTransactionamount() + " | " +td.getBalanceamount() + " | " +td.getAccountnumber());

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}
}
 
