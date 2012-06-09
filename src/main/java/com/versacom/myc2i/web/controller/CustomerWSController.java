package com.versacom.myc2i.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.versacom.myc2i.service.CustomerService;
import com.versacom.myc2i.web.message.CustomerMessage;
import com.versacom.myc2i.web.message.GenericMessageResponse;

/**
 * This class is responsible for generating JSON based RESTful web service for
 * customer related services.
 * 
 * @author Shamim Ahmmed
 * 
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerWSController extends AbstractWSController {

  @Autowired
  CustomerService customerService;

  @RequestMapping(method = RequestMethod.GET, value = "/new/id")
  public void generateNewCustomerId(HttpServletResponse response)
      throws HttpMessageNotWritableException, IOException {

    logger.debug("Generating New customerid ");

    GenericMessageResponse<String> message = new GenericMessageResponse<String>();
    String customerId = customerService.generateCustomerId();
    message.setResult(customerId);

    this.writeObjectAsJsonString(response, message);

  }

  @RequestMapping(method = RequestMethod.POST, value = "/new")
  public void storeNewCustomerId(HttpServletResponse response,
      @RequestBody CustomerMessage request)
      throws HttpMessageNotWritableException, IOException {

    logger.debug("Storing customer information ");

    GenericMessageResponse<String> message = new GenericMessageResponse<String>();
    customerService.createNewCustomer(request);
    message.setResult(request.getCustomerId());

    this.writeObjectAsJsonString(response, message);

  }
}
