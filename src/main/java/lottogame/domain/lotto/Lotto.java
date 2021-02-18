package lottogame.domain.lotto;

import lottogame.utils.InvalidWinningLottoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> values) {
        values.stream()
                .filter(value -> !LottoGenerator.validNumber(value))
                .findAny()
                .ifPresent(value -> {
                    throw new InvalidWinningLottoException();
                });
        numbers = values;
    }

    public List<Integer> values() {
        return new ArrayList<>(numbers);
    }

    public int match(Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(number -> winningLotto.contains(number))
                .count();
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public boolean containsBonus(WinningLotto winningLotto) {
        return numbers.contains(winningLotto.getBonusBall());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
