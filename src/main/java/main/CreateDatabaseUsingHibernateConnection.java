package main;
import java.sql.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

public class CreateDatabaseUsingHibernateConnection {

    public static void main(String[] args) throws Exception {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BankManagementSystemusingHibernet");

        SessionFactoryImplementor sfi = emf.unwrap(SessionFactoryImplementor.class);
        ConnectionProvider cp = sfi.getServiceRegistry().getService(ConnectionProvider.class);
        Connection conn = cp.getConnection();

        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS bankdb");
        System.out.println("Database Created Successfully!");

        stmt.close();
        conn.close();
        emf.close();
    }
}