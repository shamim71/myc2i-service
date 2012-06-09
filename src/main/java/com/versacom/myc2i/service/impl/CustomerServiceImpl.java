package com.versacom.myc2i.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.versacom.myc2i.dao.CustomerDao;
import com.versacom.myc2i.domain.Customer;
import com.versacom.myc2i.service.CustomerService;
import com.versacom.myc2i.web.message.CustomerMessage;

/**
 * Service layer implementation of processing customer information
 * 
 * @author Shamim Ahmmed
 * 
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

  final Logger logger = Logger.getLogger(getClass());

  @Autowired
  CustomerDao customerDao;

  /**
   * {@inheritDoc}
   * 
   * Randomly generate a unique identifier and verify if the same identifier
   * already exist. If it is exist, try to generate a new one.
   * 
   */
  @Override
  public String generateCustomerId() {
    Customer customer = null;
    String customerId;
    do {
      String value = UUID.randomUUID().toString();
      customerId = Integer.toString(Math.abs(value.hashCode()));
      /** Prevent collisions */
      customer = this.customerDao.findCustomerByCustomerId(customerId);
    }
    while (customer != null);

    return customerId;
  }

  @Override
  public void createNewCustomer(CustomerMessage customer) {
    logger.debug("Creating a new customer ");

    Customer customerNew = new Customer();
    customerNew.setCustomerId(customer.getCustomerId());
    customerNew.setName(customer.getName());
    customerNew.setLastModified(new Date());
    customerNew.setRecordCreated(new Date());

    this.customerDao.persist(customerNew);

  }

  @Override
  public CustomerMessage findCustomer(String customerId) {
    Customer customer = this.customerDao.findCustomerByCustomerId(customerId);
    CustomerMessage msg = new CustomerMessage();
    msg.setCustomerId(customerId);
    msg.setName(customer.getName());
    return msg;
  }

}
