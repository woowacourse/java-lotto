package lotto.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {
    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(final Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

//    public LottoTicket(final Set<Integer> lottoNumbers) {
//        this.lottoNumbers = lottoNumbers
//            .stream()
//            .map(LottoNumber::new)
//            .collect(Collectors.toSet());
//    }

    public Set<Integer> getLottoNumbers() {
        Set<Integer> numbers = new HashSet<>();
        lottoNumbers.forEach(lottoNumber -> numbers.add(lottoNumber.getNumber()));
        return numbers;
    }

    public int compareNumbers(LottoTicket winningTicket) {
        Set<LottoNumber> hitLottoNumbers = new HashSet<>();
        hitLottoNumbers.addAll(lottoNumbers);
        hitLottoNumbers.retainAll(winningTicket.lottoNumbers);
        return hitLottoNumbers.size();
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
