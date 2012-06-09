package com.versacom.myc2i.dao;

import com.versacom.myc2i.domain.Customer;

/**
 * Data access layer interface for accessing customer entity
 * 
 * @author Shamim Ahmmed
 * 
 */
public interface CustomerDao extends GenericDao<Customer, Long> {

  /**
   * Find the Customer information by customer Id
   * 
   * @param customerId
   *          the customer id
   * 
   * @return the generated Customer instance
   */
  Customer findCustomerByCustomerId(final String customerId);
}
