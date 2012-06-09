package com.versacom.myc2i.service;

/**
 * Custom exception class to generate HTTP 404 error
 * 
 * @author Shamim Ahmmed
 * 
 */
public class ItemNotFoundException extends ServiceException {

  public ItemNotFoundException(String message, String errorCode,
      int httpErrorCode) {
    super(message, errorCode, httpErrorCode);
  }

  private static final long serialVersionUID = 1L;
}
