package com.versacom.myc2i.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.versacom.myc2i.dao.CustomerDao;
import com.versacom.myc2i.domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/spring/customer-dao-test.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class CustomerDaoIntegrationTest extends
    AbstractTransactionalJUnit4SpringContextTests {

  @Autowired
  CustomerDao customerDao;

  @Test
  public void testCustomer() {
    List<Customer> customers = customerDao.loadAll();
    int size = customers.size();
    Customer customer = new Customer();
    customer.setCustomerId("123456");
    customer.setName("Mr. X-Man");

    customerDao.persist(customer);

    customers = customerDao.loadAll();
    assertEquals("Check customer has been created", size + 1, customers.size());
  }
}
