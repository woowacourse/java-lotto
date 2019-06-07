package lotto.domain;

import java.util.*;

public class Lotto {
    private static final String ERROR_DUPLICATE_MESSAGE = "중복된 수가 있습니다.";
    private static final String ERROR_LOTTO_SIZE = "가질수 있는 로또의 수는 6개 입니다.";
    private static final int LOTTO_SIZE = 6;

    private final List<Number> lotto;

    public Lotto(List<Number> lotto) {
        this.lotto = lotto;
        validCheck();
    }

    public boolean isContains(Number number) {
        return lotto.contains(number);
    }

    public int getSize() {
        return lotto.size();
    }

    public Number getLottoByIndex(int index) {
        return lotto.get(index);
    }

    private void validCheck() {
        validDuplication();
        validSize();
    }

    private void validDuplication() {
        Set<Number> cheked = new HashSet<>(lotto);

        if (cheked.size() != lotto.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_MESSAGE);
        }
    }

    private void validSize() {
        if (lotto.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE);
        }
    }

    public List<Number> getLotto() {
        return Collections.unmodifiableList(lotto);
    }

    @Override
    public String toString() {
        return lotto.toString();
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
