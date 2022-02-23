package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputController {

    public int countLotto(String price) {
        return Integer.parseInt(price) / 1000;
    }

    public List<Integer> splitWinningNumbers(String winningNumbers) {
        String[] s = winningNumbers.split(", ");
        return Arrays.stream(s)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public int toIntBonusNumber(String bonusNumber) {
        return Integer.parseInt(bonusNumber);
    }
}
