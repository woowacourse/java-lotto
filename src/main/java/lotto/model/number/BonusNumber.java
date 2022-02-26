package lotto.model.number;

import lotto.model.Lotto;

/*
 * 보너스 번호로 쓰일 수 있는 LottoNumber에 대한 Wrapper Class
 */
public class BonusNumber {
    private static final String ERROR_DUPLICATE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다";

    private final LottoNumber number;

    private BonusNumber(LottoNumber number, WinningNumbers winningNumbers) {
        checkDuplicate(number, winningNumbers);
        this.number = number;
    }

    private void checkDuplicate(LottoNumber number, WinningNumbers winningNumbers) {
        if (winningNumbers.match(number)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE);
        }
    }

    public static BonusNumber from(String input, WinningNumbers winningNumbers) {
        return new BonusNumber(LottoNumber.from(input), winningNumbers);
    }

    public boolean match(Lotto lotto) {
        return lotto.contains(this.number);
    }
}
