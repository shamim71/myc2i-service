package com.versacom.myc2i.test.service;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.versacom.myc2i.service.CustomerService;
import com.versacom.myc2i.web.message.CustomerMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/customer-service-test.xml" })
@Transactional()
public class CustomerServiceIntegrationTest {

  @Autowired
  CustomerService customerService;

  @Test
  public void testCustomerService() {

    String customerId = this.customerService.generateCustomerId();
    CustomerMessage customer = new CustomerMessage();
    customer.setCustomerId(customerId);
    customer.setName("Mr. Chaplin");
    this.customerService.createNewCustomer(customer);
    CustomerMessage msg = this.customerService.findCustomer(customerId);
    Assert.assertEquals("Both customer id should be identical", customerId,
        msg.getCustomerId());
  }

}
