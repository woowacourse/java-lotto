package lotto.controller;

import lotto.domain.lottonumber.Lotto;
import lotto.domain.lottonumber.WinningNumbers;
import lotto.domain.lottonumber.vo.LottoNumber;
import lotto.view.input.InputView;

public class WinningLottoGenerator {
    public WinningNumbers generateWinningNumbers(final InputView inputView) {
        try {
            final Lotto lastWinningNumbers = new Lotto(inputView.inputLastWeekWinningNumbers());
            final LottoNumber bonusNumber = LottoNumber.from(inputView.inputBonusNumber());
            return new WinningNumbers(lastWinningNumbers, bonusNumber);
        } catch (final Exception e) {
            inputView.printErrorMessage(e.getMessage());
            return generateWinningNumbers(inputView);
        }
    }
}
