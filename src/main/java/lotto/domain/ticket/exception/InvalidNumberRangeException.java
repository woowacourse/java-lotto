package lotto.domain.ticket.exception;

public class InvalidNumberRangeException extends RuntimeException {
    public InvalidNumberRangeException(String message){
        super(message);
    }
}
