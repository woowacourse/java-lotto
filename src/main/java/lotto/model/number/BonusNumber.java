package lotto.model.number;

import lotto.model.Lotto;

public class BonusNumber implements LottoNumber {
    private static final String ERROR_TYPE = "[ERROR] 보너스 번호는 숫자로만 입력해주세요";
    private static final String ERROR_DUPLICATE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다";

    private final int number;

    private BonusNumber(int number, WinningNumbers winningNumbers) {
        validate(number, winningNumbers);
        this.number = number;
    }

    private void validate(int number, WinningNumbers winningNumbers) {
        checkBound(number);
        checkDuplicate(number, winningNumbers);
    }

    private void checkDuplicate(int number, WinningNumbers winningNumbers) {
        if (winningNumbers.match(number)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE);
        }
    }

    public static BonusNumber from(String input, WinningNumbers winningNumbers) {
        try {
            return new BonusNumber(Integer.parseInt(input), winningNumbers);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_TYPE);
        }
    }

    public boolean match(Lotto lotto) {
        return lotto.contains(this.number);
    }
}
