package com.versacom.myc2i.web.controller;

import static com.versacom.myc2i.common.ServiceConstants.ERROR_CODE_INVALID_MESSAGE_STRUCTURE;
import static com.versacom.myc2i.common.ServiceConstants.ERROR_CODE_NULL_POINTER_ENCOUNTERED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.versacom.myc2i.service.ItemNotFoundException;
import com.versacom.myc2i.service.ServiceException;
import com.versacom.myc2i.web.message.ErrorMessage;
import com.versacom.myc2i.web.message.GenericMessageResponse;

/**
 * This class contains common methods and exception handler methods that will be
 * used all controller layer classes
 * 
 * @author Shamim Ahmmed
 * 
 */
public abstract class AbstractWSController {

  final Logger logger = Logger.getLogger(getClass());

  @Autowired
  Jaxb2Marshaller jaxb2Marshaller;

  protected void writeObjectAsJsonString(HttpServletResponse response,
      GenericMessageResponse<?> message)
      throws HttpMessageNotWritableException, IOException {
    MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
    response.setContentType(CONTENT_TYPE_JSON);
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);

    converter.write(message, new MediaType("application", "json"),
        outputMessage);
  }

  protected void writeObjectAsJsonString(HttpServletResponse response,
      Object message) throws HttpMessageNotWritableException, IOException {
    MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
    response.setContentType(CONTENT_TYPE_JSON);
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);

    converter.write(message, new MediaType("application", "json"),
        outputMessage);
  }

  protected void writeObjectAsXMLString(HttpServletResponse response,
      Object message) throws HttpMessageNotWritableException, IOException {
    MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter();
    converter.setMarshaller(jaxb2Marshaller);
    response.setContentType(CONTENT_TYPE_XML);
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);

    converter
        .write(message, new MediaType("application", "xml"), outputMessage);
  }

  /**
   * Transform the service related exception into a standard HTTP style
   * exception along with application level error code and error message
   * 
   * @param ex
   *          the instance of GiftServiceException
   * @param response
   *          the HttpServletResponse instance
   * 
   * @throws HttpMessageNotWritableException
   * @throws IOException
   */
  @ExceptionHandler(ServiceException.class)
  public void handleServiceException(ServiceException ex,
      HttpServletResponse response) throws HttpMessageNotWritableException,
      IOException {

    GenericMessageResponse<?> msg = new GenericMessageResponse<String>();
    msg.setResult(null);
    msg.setError(new ErrorMessage(ex.getErrorCode(), ex.getMessage()));
    this.writeObjectAsJsonString(response, msg, ex.getHttpErrorCode());
  }

  /**
   * Transform the ItemNotFoundException to standard HTTP style JSON format
   * 
   * @param ex
   *          the ItemNotFoundException instance
   * @param response
   *          the HttpServletResponse instance
   * 
   * @throws HttpMessageNotWritableException
   * @throws IOException
   */
  @ExceptionHandler(ItemNotFoundException.class)
  public void handleNotFoundException(ItemNotFoundException ex,
      HttpServletResponse response) throws HttpMessageNotWritableException,
      IOException {

    GenericMessageResponse<?> msg = new GenericMessageResponse<String>();
    msg.setResult(null);
    msg.setError(new ErrorMessage(ex.getErrorCode(), ex.getMessage()));
    this.writeObjectAsJsonString(response, msg, ex.getHttpErrorCode());
  }

  /**
   * Transform the NullPointerException to standard HTTP style JSON format
   * 
   * @param ex
   *          the NullPointerException instance
   * @param response
   *          the HttpServletResponse instance
   * 
   * @throws HttpMessageNotWritableException
   * @throws IOException
   */
  @ExceptionHandler(NullPointerException.class)
  public void handleNullPointerException(NullPointerException ex,
      HttpServletResponse response) throws HttpMessageNotWritableException,
      IOException {

    GenericMessageResponse<?> msg = new GenericMessageResponse<String>();
    msg.setResult(null);
    String warning = "We have encountered a problem during processing your request, "
        + ex.getMessage();
    msg.setError(new ErrorMessage(ERROR_CODE_NULL_POINTER_ENCOUNTERED, warning));
    this.writeObjectAsJsonString(response, msg, INTERNAL_SERVER_ERROR.value());
  }

  /**
   * Transform the HttpMessageNotReadableException to standard HTTP style JSON
   * format
   * 
   * @param ex
   *          the NullPointerException instance
   * @param response
   *          the HttpServletResponse instance
   * 
   * @throws HttpMessageNotWritableException
   * @throws IOException
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public void handleInvalidMessagePerceived(HttpMessageNotReadableException ex,
      HttpServletResponse response) throws HttpMessageNotWritableException,
      IOException {

    GenericMessageResponse<?> msg = new GenericMessageResponse<String>();
    msg.setResult(null);
    String warning = " Please check your request message format and try again";

    logger.error(ex.getMessage(), ex);

    msg.setError(new ErrorMessage(ERROR_CODE_INVALID_MESSAGE_STRUCTURE, warning));
    this.writeObjectAsJsonString(response, msg, BAD_REQUEST.value());
  }

  /**
   * Write an instance of GenericMessageResponse into the response along with
   * the status in the response header
   * 
   * @param response
   *          the HttpServletResponse instance
   * @param message
   *          the GenericMessageResponse instance
   * @param status
   *          the HTTP status code
   * 
   * @throws HttpMessageNotWritableException
   * @throws IOException
   */
  protected void writeObjectAsJsonString(HttpServletResponse response,
      GenericMessageResponse<?> message, int status)
      throws HttpMessageNotWritableException, IOException {
    MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
    response.setContentType(CONTENT_TYPE_JSON);
    response.setStatus(status);
    HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);

    converter.write(message, new MediaType("application", "json"),
        outputMessage);
  }

  private static final String CONTENT_TYPE_JSON = "application/json";
  private static final String CONTENT_TYPE_XML = "application/xml";
}
