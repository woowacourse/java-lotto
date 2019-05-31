package lotto.domain;

public class InvalidLottoNumberException extends IllegalArgumentException {
    public InvalidLottoNumberException(String s) {
        super(s);
    }
}
