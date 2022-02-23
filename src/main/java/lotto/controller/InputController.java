package lotto.controller;

import lotto.util.InputValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputController {

    public static final int PRICE_PER_LOTTO = 1000;

    public int countLotto(String price) {
        InputValidator.validatePrice(price);
        return Integer.parseInt(price) / PRICE_PER_LOTTO;
    }

    public List<Integer> splitWinningNumbers(String winningNumbers) {
        InputValidator.validateWinningNumbers(winningNumbers);
        return Arrays.stream(winningNumbers.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public int toIntBonusNumber(String bonusNumber, List<Integer> winningNumbers) {
        InputValidator.validateBonusNumber(bonusNumber, winningNumbers);
        return Integer.parseInt(bonusNumber);
    }
}
