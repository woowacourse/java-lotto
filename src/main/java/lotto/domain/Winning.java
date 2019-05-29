package lotto.domain;

import java.util.List;
import java.util.Objects;

public class Winning {
    private final Lotto lotto;

    private Winning(List<Integer> numbers) {
        this.lotto = Lotto.of(numbers);
    }

    public static Winning of(List<Integer> numbers) {
        return new Winning(numbers);
    }

    public Rank checkWinner(Lotto lotto) {
        return Rank.valueOf(this.lotto.sameNumberCount(lotto));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Winning winning = (Winning) o;
        return Objects.equals(lotto, winning.lotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto);
    }
}
