package lotto.model;

import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;

/*
 * 자동 로또 한 장을 의미하는 Class
 */
public class Lotto {
    private static final String ERROR_UNIT = "[ERROR] 구매 금액은 1000원 단위로 입력하세요";
    private static final int PRICE = 1000;

    private LottoNumbers numbers;

    public Lotto() {
        this.numbers = LottoNumbers.ofRandomNumbers();
    }

    public static int countAvailableTickets(Money money) {
        checkUnit(money);
        return money.countAvailable(PRICE);
    }

    private static void checkUnit(Money money) {
        if (!money.isUnit(PRICE)) {
            throw new IllegalArgumentException(ERROR_UNIT);
        }
    }

    public boolean contains(LottoNumber number) {
        return this.numbers.contains(number);
    }

    public int match(LottoNumbers comparingNumbers) {
        return this.numbers.match(comparingNumbers);
    }

    public LottoNumbers getNumbers() {
        return this.numbers;
    }
}
