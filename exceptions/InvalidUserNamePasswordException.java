package exceptions;

public class InvalidUserNamePasswordException extends Exception{
    public InvalidUserNamePasswordException(String message){
        super(message);
    }
}
