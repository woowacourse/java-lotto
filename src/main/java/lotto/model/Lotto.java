package lotto.model;

import java.util.List;

import lotto.util.InputValidator;

public class Lotto {

    public static final int LOTTO_PRICE = 1000;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        this.numbers = InputValidator.validateLotto(numbers);
    }

    public Rank calculateRank(WinningLotto winningLotto) {
        return winningLotto.calculate(numbers);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    public boolean contains(LottoNumber bonusNumber) {
        return numbers.contains(bonusNumber);
    }
}
