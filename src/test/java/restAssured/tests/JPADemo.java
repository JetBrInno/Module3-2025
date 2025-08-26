package restAssured.tests;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.jupiter.api.Test;
import restAssured.entitiesDB.Employee;
import restAssured.helper.EnvHelper;
import restAssured.manager.MyPUI;

import java.io.IOException;
import java.util.Properties;

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

       Employee employee =  entityManager.find(Employee.class, 55);
    System.out.println(employee);
    }
}
