package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {
    private static final int LOTTO_MONEY = 1000;

    private int remainMoney;

    LottoMachine() {
        this(0);
    }

    LottoMachine(final int money) {
        remainMoney = money;
    }

    Lotto buy(final List<Integer> numbers) {
        this.remainMoney -= LOTTO_MONEY;
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(number -> LottoNumber.of(number))
                .collect(Collectors.toList());

        return Lotto.of(lottoNumbers);
    }

    void insertMoney(final int money) {
        this.remainMoney += money;
    }

    double remainBuyCount() {
        return (this.remainMoney / LOTTO_MONEY);
    }
}
