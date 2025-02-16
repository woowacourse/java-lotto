package domain;

import java.util.Collections;
import java.util.List;

public class WinningLotto {

    private final List<Integer> numbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> numbers, int bonusNumber) {
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
