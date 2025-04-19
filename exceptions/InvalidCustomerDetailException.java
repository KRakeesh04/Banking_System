package exceptions;

public class InvalidCustomerDetailException extends Exception{
    public InvalidCustomerDetailException(String message){
        super(message);
    }
}
