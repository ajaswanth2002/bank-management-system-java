package service;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import main.Main;
import dao.TransactionDAO;
import dto.TransactionDetails;

public class TransactionService {
	// TODO Auto-generated constructor stub
	TransactionDAO transactionDAO=new TransactionDAO();
	public  void inserttransactionDetails(TransactionDetails transactionDetails) throws Exception 
	{
		if(!transactionDAO.insertTransationvalues(transactionDetails))
			System.out.println("Server Error 500");
	}
	public List<TransactionDetails> getAllTransactionByAccountNumber(long accountNumber) throws Exception {
    List<TransactionDetails> list = new ArrayList<>();
    try {
        EntityManager em = Main.em;
        em.getTransaction().begin(); 
        Query query = em.createQuery("SELECT * FROM transaction_details WHERE trans_accno=? ORDER BY trans_date, trans_time");
        query.setParameter(1, accountNumber);
        }
    	catch (Exception e) {
	      e.printStackTrace();
	    }
	    return list;
	}
}


