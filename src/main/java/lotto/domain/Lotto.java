package lotto.domain;

import java.util.List;
import java.util.Objects;

public class Lotto {

    private List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = invalidNumberOfLotto(lottoNumbers);
    }

    private List<LottoNumber> invalidNumberOfLotto(List<LottoNumber> lottoNumbers){
        if(lottoNumbers.size() != 6){
            throw new IllegalArgumentException("로또 범위는 6개여야 합니다.");
        }
        return lottoNumbers;
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
}
