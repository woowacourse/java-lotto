package lotto.exceptions;

public class LottoNumberDuplicatedException extends RuntimeException {
    public LottoNumberDuplicatedException(String message) {
        super(message);
    }
}
