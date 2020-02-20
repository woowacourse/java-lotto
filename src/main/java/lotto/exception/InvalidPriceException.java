package lotto.exception;

public class InvalidPriceException extends IllegalArgumentException {
    public InvalidPriceException(String pricePerLottoErrorMsg) {
        super(pricePerLottoErrorMsg);
    }
}
