package br.com.sidlar.dailyquiz;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Admilson
 */
public class GeraTabelas {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.
                createEntityManagerFactory("thePersistenceUnit");
        factory.close();
    }

}