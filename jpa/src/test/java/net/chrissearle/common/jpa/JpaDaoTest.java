package net.chrissearle.common.jpa;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;

public class JpaDaoTest extends JpaDao<Long, Model> {
    private Long id;

    private static final String TEST_MODEL_NAME = "Test Model";

    @BeforeTest
    protected void initialize() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-DAO-Test");
        entityManager = emf.createEntityManager();
    }

    @Test
    public void testCreate() {
        Model model = new Model();

        model.setName(TEST_MODEL_NAME);

        persist(model);

        assert entityManager.contains(model) : "Model was not persisted";

        id = model.getId();

        assert id != null : "Id was null";
    }

    @Test(dependsOnMethods = {"testCreate"})
    public void testRetrieve() {
        Model model = findById(id);

        assert model != null : "Model was null";

        assert TEST_MODEL_NAME.equals(model.getName()) : "Model had incorrect name : " + model.getName();
    }

    @Test(dependsOnMethods = {"testRetrieve"})
    public void testRemove() {
        Model model = findById(id);

        remove(model);

        assert !entityManager.contains(model) : "Model was not removed";
    }

}
