package lotto.domain;

import java.util.Objects;

class Lotto implements Ticket {
    private final LottoNumbers lottoNumbers;

    Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    @Override
    public LottoNumbers ticketNumbers() {
        return lottoNumbers;
    }

    @Override
    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
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
