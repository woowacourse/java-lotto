package lotto.controller;

import lotto.view.InputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputController {

    public int scanLottoCount() {
        return Integer.parseInt(InputView.inputPrice()) / 1000;
    }

    public List<Integer> splitWinningNumbers() {
        String[] s = InputView.inputWinningNumbers().split(", ");
        return Arrays.stream(s)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public int scanBonusNumber() {
        return Integer.parseInt(InputView.inputBonusNumber());
    }
}
