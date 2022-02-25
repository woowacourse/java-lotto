package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.model.Lottos;
import lotto.model.WinningLotto;
import lotto.util.InputValidator;

public class InputController {

    public Lottos makeLottos(String money) throws RuntimeException {
        return new Lottos(InputValidator.validateMoney(money));
    }

    public WinningLotto makeWinningLotto(String winningNumbers, String bonusNumber) {
        List<Integer> lottoNumbers = splitWinningNumbers(winningNumbers);
        int lottoBonusNumber = toIntBonusNumber(bonusNumber);
        return new WinningLotto(lottoNumbers, lottoBonusNumber);
    }

    private List<Integer> splitWinningNumbers(String winningNumbers) {
        String[] splitNumbers = InputValidator.validateLottoNumbers(winningNumbers);
        return Arrays.stream(splitNumbers)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    private int toIntBonusNumber(String bonusNumber) {
        return InputValidator.validateNumber(bonusNumber);
    }
}
