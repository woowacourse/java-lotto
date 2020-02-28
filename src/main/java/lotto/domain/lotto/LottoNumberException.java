package lotto.domain.lotto;

public class LottoNumberException extends IllegalArgumentException {

    private static String WRONG_LOTTO_NUMBER = "로또는 1부터 45까지의 숫자만 가능합니다.";

    LottoNumberException() {
        super(WRONG_LOTTO_NUMBER);
    }
}
