package lottogame.domain.lotto;

import java.util.*;

public class Lotto {
    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> lotto) {
        validate(lotto);
        this.lotto = lotto;
    }

    private static void validate(List<LottoNumber> lotto) {
        Set<LottoNumber> numbers = new HashSet<>(lotto);
        if (numbers.size() != lotto.size()) {
            throw new IllegalArgumentException("로또번호는 서로 달라야합니다.");
        }
    }

    public List<LottoNumber> values() {
        return new ArrayList<>(lotto);
    }

    public int match(Lotto lotto) {
        return (int) this.lotto.stream()
                .filter(lotto::contains)
                .count();
    }

    public boolean contains(LottoNumber lottonumber) {
        return lotto.contains(lottonumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(this.lotto, lotto.lotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto);
    }
}
