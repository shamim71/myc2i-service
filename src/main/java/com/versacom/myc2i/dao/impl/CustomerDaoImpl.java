package com.versacom.myc2i.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.versacom.myc2i.dao.CustomerDao;
import com.versacom.myc2i.domain.Customer;

/**
 * Customer data access layer implementation based on JPA
 * 
 * @author Shamim Ahmmed
 * 
 */
@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer, Long> implements
    CustomerDao {

  /**
   * {@inheritDoc}
   * 
   * This implementation load the customer information from the database based
   * JPA based API.
   */
  @Override
  public Customer findCustomerByCustomerId(String customerId) {
    final String queryClause = " t.customerId =?1";
    List<Customer> customers = this.loadByClause(queryClause,
        new String[] { customerId });
    if (customers == null || customers.size() == 0) {
      return null;
    }
    return customers.get(0);
  }

}
