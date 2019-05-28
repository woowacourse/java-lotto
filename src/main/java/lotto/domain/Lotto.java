package lotto.domain;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Lotto {
    public static final int LOTTO_NO_SIZE = 6;
    private final Set<LottoNo> lottoNos;

    private Lotto(final List<LottoNo> lottoNos) {
        this.lottoNos = new TreeSet<>(lottoNos);
        validateSize(this.lottoNos);
    }

    private void validateSize(final Set<LottoNo> lottoNos) {
        if (lottoNos.size() != LOTTO_NO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_NO_SIZE + "개만 가능합니다.");
        }
    }

    public static Lotto of(final List<LottoNo> lottoNos) {
        return new Lotto(lottoNos);
    }

    public boolean isMatch(final LottoNo lottoNo) {
        return lottoNos.contains(lottoNo);
    }

    public int countOfMatch(final Lotto anotherLotto) {
        int countOfMatch = 0;
        for (final LottoNo lottoNo : lottoNos) {
            countOfMatch += anotherLotto.isMatch(lottoNo) ? 1 : 0;
        }
        return countOfMatch;
    }

    @Override
    public String toString() {
        return lottoNos.toString();
    }
}
