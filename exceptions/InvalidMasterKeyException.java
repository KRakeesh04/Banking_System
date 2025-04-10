package exceptions;

public class InvalidMasterKeyException extends Exception{
    public InvalidMasterKeyException(String message){
        super(message);
    }
}
