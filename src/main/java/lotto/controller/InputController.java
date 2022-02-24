package lotto.controller;

import lotto.util.InputValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputController {

    private static final int PRICE_PER_LOTTO = 1000;

    public int countLotto(String price) throws RuntimeException {
        return InputValidator.validatePrice(price) / PRICE_PER_LOTTO;
    }

    public List<Integer> splitWinningNumbers(String winningNumbers) throws RuntimeException {
        return Arrays.stream(InputValidator.validateWinningNumbers(winningNumbers))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public int toIntBonusNumber(String bonusNumber, List<Integer> winningNumbers) throws RuntimeException {
        InputValidator.validateBonusNumber(bonusNumber, winningNumbers);
        return Integer.parseInt(bonusNumber);
    }
}
