package lotto.domain;

public class InvalidLottoPrice extends RuntimeException {
    public InvalidLottoPrice(String message) {
        super(message);
    }
}
