package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto implements LottoTicket {
    static final int LOTTO_PRICE = 1000;
    static final int LOTTO_SIZE = 6;
    private final List<LottoNo> lotto;

    private Lotto(final List<LottoNo> lotto) {
        if (checkDuplicate(lotto)) {
            throw new IllegalArgumentException("중복된 로또 번호가 존재합니다.");
        }
        Collections.sort(lotto);
        this.lotto = lotto;
    }

    static Lotto of(LottoGenerator lottoGenerator) {
        if (lottoGenerator.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(String.format("로또의 크기는 %d 입니다.", LOTTO_SIZE));
        }
        List<LottoNo> lotto = new ArrayList<>();
        for (int position = 0; position < LOTTO_SIZE; position++) {
            lotto.add(lottoGenerator.generate());
        }
        return new Lotto(lotto);
    }

    private boolean checkDuplicate(List<LottoNo> lotto) {
        Set<LottoNo> lottoSet = new HashSet<>(lotto);
        return lotto.size() != lottoSet.size();
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
