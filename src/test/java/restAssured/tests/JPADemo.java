package restAssured.tests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.jupiter.api.Test;
import restAssured.entitiesDB.Employee;
import restAssured.helper.EnvHelper;
import restAssured.manager.MyPUI;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JPADemo {

    private static EntityManager entityManager;
    private static EnvHelper envHelper;

    @Test
    public void demo() throws IOException {
        envHelper = new EnvHelper();
        Properties properties = envHelper.getProperties();
        PersistenceUnitInfo myPui = new MyPUI(properties);
        HibernatePersistenceProvider hibernatePersistenceProvider = new HibernatePersistenceProvider();
        EntityManagerFactory emf = hibernatePersistenceProvider.createContainerEntityManagerFactory(myPui, myPui.getProperties());
        entityManager = emf.createEntityManager();

        // Employee employee =  entityManager.find(Employee.class, 55);

        Employee employee = new Employee();
        employee.setCity("Kazan");
        employee.setPosition("Cleaning");
        employee.setSurname("Valerin");
        employee.setName("Maks");

        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();

        int id = employee.getId();

        entityManager.getTransaction().begin();
        employee = entityManager.find(Employee.class, id);
        entityManager.getTransaction().commit();
        assertNotNull(employee);
        System.out.println(employee);

       entityManager.getTransaction().begin();
      // entityManager.persist(employee);
        entityManager.remove(employee);
       entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        employee = entityManager.find(Employee.class, id);
        entityManager.getTransaction().commit();
        assertNull(employee);

    System.out.println(employee);
    }

    @Test
    public void demo2() throws IOException {
        envHelper = new EnvHelper();
        Properties properties = envHelper.getProperties();
        PersistenceUnitInfo myPui = new MyPUI(properties);
        HibernatePersistenceProvider hibernatePersistenceProvider = new HibernatePersistenceProvider();
        EntityManagerFactory emf = hibernatePersistenceProvider.createContainerEntityManagerFactory(myPui, myPui.getProperties());
        entityManager = emf.createEntityManager();

        // Employee employee =  entityManager.find(Employee.class, 55);

        Employee employee = new Employee();
        employee.setCity("Kazan");
        employee.setPosition("Cleaning");
        employee.setSurname("Valerin");
        employee.setName("Maks123");

        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        employee = getByName(employee.getName());
        entityManager.getTransaction().commit();
        assertNotNull(employee);

        System.out.println(employee);
    }

    private Employee getByName(String name) {
        TypedQuery<Employee> query = entityManager.createQuery("SELECT em FROM Employee em WHERE em.name = :employeeName", Employee.class);
        query.setParameter("employeeName", name);
        return query.getSingleResult();
    }
}
