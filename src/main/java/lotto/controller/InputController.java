package lotto.controller;

import java.util.List;
import lotto.model.Lottos;
import lotto.model.WinningLotto;
import lotto.util.InputValidator;

public class InputController {

    public Lottos makeLottos(String price) throws RuntimeException {
        return new Lottos(InputValidator.validatePrice(price));
    }

    public WinningLotto makeWinningLotto(String winningNumbers, String bonusNumber) {
        List<Integer> lottoNumbers = InputValidator.validateWinningNumbers(winningNumbers);
        int lottoBonusNumber = InputValidator.validateBonusNumber(bonusNumber, lottoNumbers);
        return new WinningLotto(lottoNumbers, lottoBonusNumber);
    }
}
