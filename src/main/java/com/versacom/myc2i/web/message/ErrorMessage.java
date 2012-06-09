package com.versacom.myc2i.web.message;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represent the generic error message which contains the error code
 * and error message
 * 
 * @author Shamim Ahmmed
 * 
 */
@XmlRootElement(name = "error")
public class ErrorMessage {

  private String code;

  private String message;

  public ErrorMessage() {
    super();
  }

  public ErrorMessage(String code, String message) {
    super();
    this.code = code;
    this.message = message;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param message
   *          the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * @param code
   *          the code to set
   */
  public void setCode(String code) {
    this.code = code;
  }

}
