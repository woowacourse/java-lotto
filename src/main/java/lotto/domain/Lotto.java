package lotto.domain;

import java.util.List;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public boolean containBonusNumber(BonusNumber bonusNumber) {
        return bonusNumber.isMatch(numbers);
    }
}
