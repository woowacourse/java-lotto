package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputController {

    public static final int PRICE_PER_LOTTO = 1000;

    public int countLotto(String price) {
        return Integer.parseInt(price) / PRICE_PER_LOTTO;
    }

    public List<Integer> splitWinningNumbers(String winningNumbers) {
        return Arrays.stream(winningNumbers.split(", "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public int toIntBonusNumber(String bonusNumber) {
        return Integer.parseInt(bonusNumber);
    }
}
