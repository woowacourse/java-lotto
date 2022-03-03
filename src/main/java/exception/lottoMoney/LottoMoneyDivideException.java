package exception.lottoMoney;

public class LottoMoneyDivideException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "로또 구입 금액은 1000 단위여야 합니다.";

    public LottoMoneyDivideException() {
        super(ERROR_MESSAGE);
    }
}
