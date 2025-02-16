package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.util.ExceptionHandler;
import lotto.util.StringConverter;

public class WinningLottoService {

    public WinningLotto createWinningLotto(Lotto winningNumbers, String input) {
        int bonusBall = StringConverter.parseToInt(input, ExceptionHandler.INVALID_BONUS_NUMBER_FORMAT);
        return new WinningLotto(winningNumbers, bonusBall);
    }
}
