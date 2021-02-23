package lottogame.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> values) {
        numbers = values;
    }

    public List<LottoNumber> values() {
        return new ArrayList<>(numbers);
    }

    public int match(Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(number -> winningLotto.contains(number))
                .count();
    }

    public boolean contains(LottoNumber lottonumber) {
        return numbers.contains(lottonumber);
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
