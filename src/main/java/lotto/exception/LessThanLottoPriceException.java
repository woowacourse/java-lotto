package lotto.exception;

public class LessThanLottoPriceException extends IllegalArgumentException {

    public LessThanLottoPriceException() {
        super("[ERROR] " + "로또 가격이 1000원이므로, 구입 가격은 1000원부터 시작입니다.");
    }
}
