//package org.bank.service;
package exception;
public class CustomerInvalidDataException extends RuntimeException {
    public CustomerInvalidDataException() {
        super("Invalid Customer Operation");
    }
    public CustomerInvalidDataException(String message) {
        super(message);
    }
    public CustomerInvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
   public CustomerInvalidDataException(Throwable cause) {
        super(cause);
    }
}

