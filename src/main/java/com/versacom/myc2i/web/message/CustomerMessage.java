package com.versacom.myc2i.web.message;

public class CustomerMessage {

  private String customerId;

  private String name;

  /**
   * @return the customerId
   */
  public String getCustomerId() {
    return customerId;
  }

  /**
   * @param customerId
   *          the customerId to set
   */
  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
}
