package lotto.domain;

import java.util.Objects;

public class Winning {
    private final Lotto lotto;

    private Winning(Lotto lotto) {
        this.lotto = lotto;
    }

    public static Winning of(Lotto lotto) {
        return new Winning(lotto);
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
