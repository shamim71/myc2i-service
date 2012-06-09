package com.versacom.myc2i.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Entity that represent the customer information
 * 
 * @author Shamim Ahmmed
 * 
 */
@Entity
@Table(name = "customer", uniqueConstraints = @UniqueConstraint(columnNames = { "customer_id" }))
public class Customer implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "customer_id", length = 100)
  private String customerId;

  @Column(name = "name", length = 255)
  private String name;

  @Column(name = "record_created")
  private Date recordCreated;

  @Column(name = "last_modified")
  private Date lastModified;

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

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
   * @return the recordCreated
   */
  public Date getRecordCreated() {
    return recordCreated;
  }

  /**
   * @param recordCreated
   *          the recordCreated to set
   */
  public void setRecordCreated(Date recordCreated) {
    this.recordCreated = recordCreated;
  }

  /**
   * @return the lastModified
   */
  public Date getLastModified() {
    return lastModified;
  }

  /**
   * @param lastModified
   *          the lastModified to set
   */
  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
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
