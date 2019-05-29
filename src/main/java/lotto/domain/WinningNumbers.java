package lotto.domain;

import java.util.List;

import lotto.domain.lotto.Number;
import lotto.utils.InputParser;

public class WinningNumbers {
    private final List<Number> winningNumbers;

    public WinningNumbers(String input) {
        this.winningNumbers = InputParser.parseLotto(input);
    }

    public boolean contains(Number number) {
        return winningNumbers.contains(number);
    }
}
