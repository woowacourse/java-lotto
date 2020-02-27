package lotto.domain.money;

public class LottoMoneyException extends IllegalArgumentException {

    private static final String NEGATIVE_MONEY = "로또 구입 금액은 음수일 수 없습니다.";
    private static final String LOTTOMONEY_NOT_BY_UNIT = "로또 구입 금액은 1000원 단위로 입력해야 합니다.";

    private LottoMoneyException(String errorMessage) {
        super(errorMessage);
    }

    static LottoMoneyException negative() {
        return new LottoMoneyException(NEGATIVE_MONEY);
    }

    static LottoMoneyException notByUnit() {
        return new LottoMoneyException(LOTTOMONEY_NOT_BY_UNIT);
    }
}
