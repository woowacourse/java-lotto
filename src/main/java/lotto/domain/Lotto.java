package lotto.domain;

import java.util.Objects;

public class Lotto {

    private final LottoNumbers lottoNumbers;

    public Lotto() {
        lottoNumbers = new LottoNumbers();
    }

    public Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = new LottoNumbers(lottoNumbers);
    }

    public LottoNumbers lotto() {
        return lottoNumbers;
    }

    public boolean containNumber(LottoNumber number) {
        return lottoNumbers.containNumber(number);
    }

    public int countOfMatchNumber(Lotto lotto) {
        return lottoNumbers.countOfMatchNumber(lotto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

    @Override
    public String toString() {
        return "Lotto : " + lottoNumbers;
    }
}
