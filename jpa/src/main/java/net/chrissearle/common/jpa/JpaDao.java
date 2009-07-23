package net.chrissearle.common.jpa;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;

public abstract class JpaDao<K, E> implements Dao<K, E> {
    protected Class<E> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public JpaDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();

		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}

	public void persist(E entity) {
        entityManager.persist(entity);
    }

	public void remove(E entity) {
        entityManager.remove(entity);
    }

	public E findById(K id) {
        return entityManager.find(entityClass, id);
    }
}
