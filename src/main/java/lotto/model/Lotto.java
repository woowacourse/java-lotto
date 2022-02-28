package lotto.model;

import java.util.List;

import lotto.util.InputValidator;

public class Lotto {

    public static final int LOTTO_PRICE = 1000;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = InputValidator.validateLotto(numbers);
    }

    public Rank calculateRank(WinningLotto winningLotto) {
        return winningLotto.calculate(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
