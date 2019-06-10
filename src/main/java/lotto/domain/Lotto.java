package lotto.domain;

import java.util.*;

public class Lotto {
    public static final int LOTTO_SIZE = 6;

    private final Set<Number> lotto;

    public Lotto(final List<Number> lotto) {
        validateSize(lotto);
        this.lotto = new TreeSet<>(lotto);
        validateOverlap();
    }

    private void validateSize(List<Number> lotto) {
        if (lotto.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또는 6자리 입니다.");
        }
    }

    private void validateOverlap() {
        if (this.lotto.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또는 중복 없이 6자리 입니다.");
        }
    }

    public List<Number> getLotto() {
        return new ArrayList<>(lotto);
    }

    @Override
    public String toString() {
        return lotto.toString();
    }

    public boolean isContain(Number number) {
        return lotto.contains(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto1 = (Lotto) o;
        return Objects.equals(lotto, lotto1.lotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto);
    }
}
