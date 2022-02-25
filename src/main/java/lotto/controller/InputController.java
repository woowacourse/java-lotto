package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.model.Lottos;
import lotto.model.WinningLotto;
import lotto.util.InputValidator;

public class InputController {

    public Lottos makeLottos(String price) throws RuntimeException {
        return new Lottos(InputValidator.validatePrice(price));
    }

    public WinningLotto makeWinningLotto(String winningNumbers, String bonusNumber) {
        List<Integer> lottoNumbers = splitWinningLotto(winningNumbers);
        int lottoBonusNumber = toIntBonusNumber(bonusNumber);
        return new WinningLotto(lottoNumbers, lottoBonusNumber);
    }

    private List<Integer> splitWinningLotto(String winningNumbers) {
        String[] splitNumbers = winningNumbers.split(",");
        return Arrays.stream(splitNumbers)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    private int toIntBonusNumber(String bonusNumber) {
        return Integer.parseInt(bonusNumber);
    }
}
