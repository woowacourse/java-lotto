package lotto.domain;

import lotto.exception.LottoCustomException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LottoTicket {

    private static final int HIT_COUNT_FOR_BONUS = 5;
    private static final int SECOND_PRIZE = 6;
    private static final int FIRST_PRIZE = 7;

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
        Set<LottoNumber> hitLottoNumbers = new HashSet<>(lottoNumbers);
        hitLottoNumbers.retainAll(winningTicket.lottoNumbers);
        if (isEqualToSecondPrize(bonusBall, hitLottoNumbers)) {
            return SECOND_PRIZE;
        }
        if (isEqualToFirstPrize(hitLottoNumbers)) {
            return FIRST_PRIZE;
        }
        return hitLottoNumbers.size();
    }

    private boolean isEqualToFirstPrize(Set<LottoNumber> hitLottoNumbers) {
        return hitLottoNumbers.size() == FIRST_PRIZE;
    }

    private boolean isEqualToSecondPrize(LottoNumber bonusBall, Set<LottoNumber> hitLottoNumbers) {
        return hitLottoNumbers.size() == HIT_COUNT_FOR_BONUS
                && lottoNumbers.contains(bonusBall);
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
