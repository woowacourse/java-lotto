package lotto.domain.ticket.exception;

public class InvalidNumberCountException extends RuntimeException {
    public InvalidNumberCountException(String message){
        super(message);
    }
}
