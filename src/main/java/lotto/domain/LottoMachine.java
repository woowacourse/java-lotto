package lotto.domain;

import lotto.domain.exception.LottoCreateArgumentException;

import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {
    public static final int LOTTO_MONEY = 1000;

    private int remainMoney;

    LottoMachine(final int money) {
        if (money < LOTTO_MONEY) {
            throw new LottoCreateArgumentException("1000원 이상 구매하세요");
        }
        remainMoney = money;
    }

    Lotto buy(final List<Integer> numbers) {
        this.remainMoney -= LOTTO_MONEY;
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(number -> LottoNumber.of(number))
                .collect(Collectors.toList());

        return Lotto.of(lottoNumbers);
    }

    boolean isRemainMoney() {
        return (this.remainMoney >= LOTTO_MONEY);
    }
}
