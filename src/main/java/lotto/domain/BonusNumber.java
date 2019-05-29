package lotto.domain;

import lotto.exceptions.BonusNumberException;
import lotto.utils.InputParser;

public class BonusNumber extends Number {
    private BonusNumber(int number) {
        super(number);
    }

    public static BonusNumber of(String input, WinningLotto winningLotto) {
        int number = InputParser.parseBonus(input);
        if (winningLotto.contains(LottoNumber.of(number))) {
            throw new BonusNumberException("이미 당첨 번호에 있는 번호입니다.");
        }
        return new BonusNumber(number);
    }

    @Override
    public void valid(int lottoNumber) {

    }
}
