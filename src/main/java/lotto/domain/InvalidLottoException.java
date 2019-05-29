package lotto.domain;

public class InvalidLottoException extends RuntimeException{
    public InvalidLottoException(String message) {
        super(message);
    }
}
