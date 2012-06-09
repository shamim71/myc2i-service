package com.versacom.myc2i.web.message;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represent a generic message response from all the server side
 * services which contains an error entity and a generic field that contains the
 * runtime result.
 * 
 * @author Shamim Ahmmed
 * 
 * @param <T>
 *          the type of the Response Result
 */
@XmlRootElement(name = "Response")
public class GenericMessageResponse<T> {

  private T result;

  private ErrorMessage error;

  /**
   * @return the result
   */
  public T getResult() {
    return result;
  }

  /**
   * @param result
   *          the result to set
   */
  public void setResult(T result) {
    this.result = result;
  }

  /**
   * @return the error
   */
  public ErrorMessage getError() {
    return error;
  }

  public GenericMessageResponse() {
    this.error = null;
  }

  /**
   * @param error
   *          the error to set
   */
  public void setError(ErrorMessage error) {
    this.error = error;
  }

}