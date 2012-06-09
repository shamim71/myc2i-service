package com.versacom.myc2i.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.versacom.myc2i.dao.GenericDao;

/**
 * This the default JPA implementation of Dao interface GenericDao that contains
 * most of dao related operation and common to all DAO interfaces
 * 
 * @author Shamim Ahmmed
 * 
 */
@Repository
public abstract class GenericDaoImpl<T, ID extends Serializable> implements
    GenericDao<T, ID> {

  protected final Class<T> persistentClass;
  protected final Logger logger = Logger.getLogger(GenericDaoImpl.class);

  protected EntityManager entityManager;

  @SuppressWarnings("unchecked")
  public GenericDaoImpl() {
    this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
        .getGenericSuperclass()).getActualTypeArguments()[0];
  }

  /**
   * Set from EntityManager instance from persistance context
   * 
   * @param entityManager
   *          the EntityManager instance
   */
  @PersistenceContext
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  /**
   * @return
   */
  public Class<T> getPersistentClass() {
    return persistentClass;
  }

  /**
   * Load the entity by identifier
   * 
   * @param id
   *          the id of the entity
   * @return the entity
   */
  @Override
  public T loadById(ID id) {
    return entityManager.find(persistentClass, id);
  }

  /**
   * Store the Entity into the storage
   * 
   * @param entity
   *          the entity instance
   */
  @Override
  public void persist(T entity) {
    entityManager.persist(entity);
  }

  /**
   * Update an existing entity
   * 
   * @param entity
   *          the entity instance
   */
  @Override
  public void update(T entity) {
    entityManager.merge(entity);
  }

  /**
   * Delete an existing entity from the storage
   * 
   * @param entity
   *          the entity instance
   */
  @Override
  public void delete(T entity) {
    entityManager.remove(entity);
  }

  /**
   * Return the list of entities matching with the JPAQL
   * 
   * @param sql
   *          the full query statement
   * @param params
   *          the parameters if the query contains any
   * 
   * @return the list of entities
   */
  @SuppressWarnings("unchecked")
  @Override
  public List<T> loadByQuery(String sql, Object[] params) {
    Query query = entityManager.createQuery(sql);
    /** bind parameters */
    for (int i = 0; params != null && i < params.length; i++) {
      query.setParameter(i + 1, params[i]);
    }
    return query.getResultList();
  }

  /**
   * Return all the entity from the storage
   * 
   * @return the list of entities
   */
  @SuppressWarnings("unchecked")
  @Override
  public List<T> loadAll() {
    return entityManager.createQuery(
        "Select t from " + persistentClass.getName() + " t").getResultList();

  }

  /**
   * Return the list of entities matching with the query clause, Note that the
   * main query will be populated based on object type.
   * 
   * @param clause
   *          the query clause after the entity name
   * @param params
   *          the parameters if the query contains any
   * 
   * @return the list of entities
   */
  @SuppressWarnings("unchecked")
  @Override
  public List<T> loadByClause(String clause, Object[] params) {
    String hsql = "Select t from " + persistentClass.getName() + " t where "
        + clause;
    // log.debug(hsql);
    Query query = entityManager.createQuery(hsql);
    /** bind parameters */
    for (int i = 0; params != null && i < params.length; i++) {
      query.setParameter(i + 1, params[i]);
    }
    return query.getResultList();

  }

}
