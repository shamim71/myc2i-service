package com.versacom.myc2i.service;

import com.versacom.myc2i.web.message.CustomerMessage;

/**
 * Define a service interface for customer related operations
 * 
 * @author Shamim Ahmmed
 * 
 */
public interface CustomerService {

  /**
   * Generate a unique customer id.
   * 
   * @return the generated customer id
   */
  String generateCustomerId();

  /**
   * Store customer into the db
   * 
   * @param customer
   */
  void createNewCustomer(CustomerMessage customer);

  /**
   * Find the customer message by customer id
   * 
   * @param customerId
   * @return
   */
  CustomerMessage findCustomer(String customerId);
}
