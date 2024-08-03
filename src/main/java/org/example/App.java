package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.configuration.CustomPersistenceUnit;
import org.example.entity.Product;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    static EntityManagerFactory managerFactory;
    static EntityManager entityManager;

    public static void main( String[] args )
    {


        try {

           // managerFactory = new HibernatePersistenceProvider()
           //         .createContainerEntityManagerFactory(new CustomPersistenceUnit(), new HashMap<>());

            managerFactory = Persistence.createEntityManagerFactory("my-PersistenceUnit");

            entityManager = managerFactory.createEntityManager(); //represents the context

            entityManager.getTransaction().begin();

            entityManager.persist(createProduct(2L, "computer"));

            entityManager.getTransaction().commit();
        }catch (Exception e){
            System.out.println("e = " + e);
        }finally {
            entityManager.close();
            managerFactory.close();
        }
    }

    public static Product createProduct(Long id, String name) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        return product;
    }
}
