package lotto.exception;

public class NotNumberException extends  IllegalArgumentException {

    public NotNumberException(String notNumberMsg) {
        super(notNumberMsg);
    }
}
