package lotto.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LottoTicket {

    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(final Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public int countHits(LottoTicket winningTicket) {
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
