package lotto.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Lotto implements Ticket {
    private final LottoNumbers lottoNumbers;

    Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    @Override
    public List<Integer> ticketNumbers() {
        return lottoNumbers.numbers().stream().map(LottoNumber::toInt).collect(Collectors.toList());
    }

    @Override
    public boolean contains(int number) {
        return ticketNumbers().contains(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return lottoNumbers.equals(lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
