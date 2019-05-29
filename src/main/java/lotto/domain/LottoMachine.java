package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {
    public static final int LOTTO_MONEY = 1000;

    private RandomNumbersGenerator generator;
    private int remainMoney;

    LottoMachine(final int money) {
        if (money < LOTTO_MONEY) {
            throw new LottoCreateArgumentException("1000원 이상 구매하세요");
        }
        generator = RandomNumbersGenerator.of(LottoNumber.MIN_NUMBER, LottoNumber.MAX_NUMBER, Lotto.LOTTO_SIZE);
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
