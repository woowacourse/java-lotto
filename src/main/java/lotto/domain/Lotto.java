package lotto.domain;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Lotto implements LottoTicket {
    static final int LOTTO_PRICE = 1000;
    static final int LOTTO_SIZE = 6;
    private final Set<LottoNo> lotto;

    private Lotto(final List<LottoNo> lotto) {
        Set<LottoNo> lottoSet = new TreeSet<>(lotto);
        if (lottoSet.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(String.format("로또는 중복되지 않은 숫자 %d개로 구성 됩니다.", LOTTO_SIZE));
        }
        this.lotto = lottoSet;
    }

    static Lotto of(final List<Integer> lotto) {
        return new Lotto(
                lotto.stream()
                        .map(LottoNo::of)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public boolean contains(LottoNo lottoNo) {
        return lotto.contains(lottoNo);
    }

    @Override
    public int includedNumber(Lotto lotto) {
        return (int) this.lotto.stream()
                .filter(lotto::contains)
                .count();
    }

    @Override
    public int size() {
        return lotto.size();
    }

    @Override
    public String toString() {
        return lotto.stream()
                .map(LottoNo::toString)
                .collect(Collectors.joining(", "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lotto)) return false;
        Lotto lotto1 = (Lotto) o;
        return Objects.equals(lotto, lotto1.lotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto);
    }
}
