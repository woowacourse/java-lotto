package lotto.domain;

import lotto.util.ExceptionHandler;
import lotto.util.StringConverter;

public class WinningLottoFactory {

    public WinningLotto createWinningLotto(Lotto winningNumbers, String input) {
        int bonusBall = StringConverter.parseToInt(input, ExceptionHandler.INVALID_BONUS_NUMBER_FORMAT);
        return new WinningLotto(winningNumbers, bonusBall);
    }
}
