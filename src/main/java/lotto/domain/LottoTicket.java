package lotto.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lotto.exception.LottoCustomException;

public class LottoTicket {

    private static final int CHECK_HIT_COUNT_HAS_BONUS = 5;
    private static final int SECOND = 6;
    private static final int FIRST = 7;

    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(final Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public Set<Integer> getLottoNumbers() {
        Set<Integer> numbers = new HashSet<>();
        lottoNumbers.forEach(lottoNumber -> numbers.add(lottoNumber.getNumber()));
        return numbers;
    }

    public int compareNumbers(LottoTicket winningTicket, LottoNumber bonusBall) {
        Set<LottoNumber> hitLottoNumbers = new HashSet<>();
        hitLottoNumbers.addAll(lottoNumbers);
        hitLottoNumbers.retainAll(winningTicket.lottoNumbers);

        if (hitLottoNumbers.size() == CHECK_HIT_COUNT_HAS_BONUS && lottoNumbers
            .contains(bonusBall)) {
            return SECOND;
        }

        if (hitLottoNumbers.size() == FIRST) {
            return FIRST;
        }
        return hitLottoNumbers.size();
    }

    public void checkDuplicateNumber(LottoNumber bonusBall) {
        if (lottoNumbers.contains(bonusBall)) {
            throw new LottoCustomException("보너스 볼은 지난 주 당첨번호와 중복될 수 없습니다.");
        }
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
