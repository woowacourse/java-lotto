package lotto.domain.lotto;

public class LottoTicketException extends IllegalArgumentException {

    private static String DUPLICATE_LOTTO_NUMBER = "로또는 중복되지 않는 6개의 숫자로 이뤄져야 합니다.";

    LottoTicketException() {
        super(DUPLICATE_LOTTO_NUMBER);
    }
}
