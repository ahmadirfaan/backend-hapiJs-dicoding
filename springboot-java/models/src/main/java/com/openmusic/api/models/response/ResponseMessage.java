
package com.openmusic.api.models.response;

import com.alipay.tracer.biz.util.ToStringUtil;
import org.springframework.http.HttpStatus;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: ResponseMessage.java, v 0.1 2021‐11‐08 19.46 Ahmad Irfaan Hibatullah Exp $$
 */
public class ResponseMessage<T> {

    private String status;
    private String message;
    private T data;

    public ResponseMessage() {
    }

    public ResponseMessage(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseMessage<T> error(String status, String message, T data) {
        return new ResponseMessage<>(status, message, data);
    }

    public static ResponseMessage<Object> error(HttpStatus status) {
        return error(String.valueOf(status.value()), status.getReasonPhrase());
    }

    public static ResponseMessage<Object> error(String code, String message) {
        return error(code, message, null);
    }

    /**
     * Getter method for property status.
     *
     * @return property value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Getter method for property message.
     *
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Getter method for property data.
     *
     * @return property value of data
     */
    public T getData() {
        return data;
    }

    /**
     * Setter method for property status.
     *
     * @param status value to be assigned to property status
     */

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Setter method for property message.
     *
     * @param message value to be assigned to property message
     */

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Setter method for property data.
     *
     * @param data value to be assigned to property data
     */

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return ToStringUtil.reflectionToLogStringByFields(this);
    }
}