package lotto.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LottoTicket {


    private static final int CHECK_HIT_COUNT_HAS_BONUS = 5;
    private static final int SECOND = 6;
    private static final int FIRST = 7;

    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(final Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public int compareNumbers(WinningLotto winningLotto) {
        int hits = countHits(winningLotto.getNumbers());

        if (hits == CHECK_HIT_COUNT_HAS_BONUS && winningLotto.hasBonus(this)) {
            return SECOND;
        }
        if (hits == SECOND) {
            return FIRST;
        }
        return hits;
    }

    private int countHits(LottoTicket winningTicket) {
        Set<LottoNumber> hitLottoNumbers = new HashSet<>(lottoNumbers);
        hitLottoNumbers.retainAll(winningTicket.lottoNumbers);
        return hitLottoNumbers.size();
    }

    public boolean hasNumber(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public Set<Integer> getLottoNumbers() {
        Set<Integer> numbers = new HashSet<>();
        lottoNumbers.forEach(lottoNumber -> numbers.add(lottoNumber.getNumber()));
        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoTicket lottoTicket = (LottoTicket) o;
        return lottoNumbers.equals(lottoTicket.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
