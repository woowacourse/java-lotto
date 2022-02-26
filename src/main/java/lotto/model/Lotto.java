package lotto.model;

import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;

/*
 * 자동 로또 한 장을 의미하는 Class
 */
public class Lotto {
    private static final int PRICE = 1000;

    private LottoNumbers numbers;

    public Lotto() {
        this.numbers = LottoNumbers.ofRandomNumbers();
    }

    public static int countAvailableTickets(Money money) {
        return money.countAvailable(PRICE);
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
