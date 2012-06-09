package com.versacom.myc2i.dao;

import java.io.Serializable;
import java.util.List;

/**
 * This class is responsible for managing entities via a generic way using java
 * Generic programming pattern.
 * 
 * @author Shamim Ahmmed
 * 
 * @param <T>
 *          the Type of the entity
 * @param <ID>
 *          the identifier of the entity
 */
public interface GenericDao<T, ID extends Serializable> {

  /**
   * Load the entity by identifier
   * 
   * @param id
   *          the id of the entity
   * @return the entity
   */
  T loadById(ID id);

  /**
   * Store the Entity into the storage
   * 
   * @param entity
   *          the entity instance
   */
  void persist(T entity);

  /**
   * Update an existing entity
   * 
   * @param entity
   *          the entity instance
   */
  void update(T entity);

  /**
   * Delete an existing entity from the storage
   * 
   * @param entity
   *          the entity instance
   */
  void delete(T entity);

  /**
   * Return all the entity from the storage
   * 
   * @return the list of entities
   */
  List<T> loadAll();

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
  List<T> loadByQuery(String sql, Object[] params);

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
  List<T> loadByClause(String clause, Object[] params);

}
