package lottogame.domain.lotto;

import java.util.*;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> values) {
        validate(values);
        numbers = values;
    }

    private static void validate(List<LottoNumber> lotto) {
        Set<LottoNumber> numbers = new HashSet<>(lotto);
        if (numbers.size() != lotto.size()) {
            throw new IllegalArgumentException("로또번호는 서로 달라야합니다.");
        }
    }

    public List<LottoNumber> values() {
        return new ArrayList<>(numbers);
    }

    public int match(Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto::contains)
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
