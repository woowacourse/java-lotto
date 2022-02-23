package lotto.domain;

import java.util.List;
import lotto.receiver.WinningNumbersReceiver;

public class WinningNumbers {

    private final List<LottoNumber> winningNumbers;

    public WinningNumbers(String input) {
        this.winningNumbers = WinningNumbersReceiver.receive(input);
    }
}
