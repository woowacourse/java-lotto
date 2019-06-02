package lotto.domain.lotto;

import lotto.domain.InvalidLottoException;
import lotto.util.AscendingNumber;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lotto{

    private List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = invalidNumberOfLotto(lottoNumbers);
        Collections.sort(lottoNumbers, new AscendingNumber());
    }

    private List<LottoNumber> invalidNumberOfLotto(List<LottoNumber> lottoNumbers){
        if(lottoNumbers.size() != 6){
            throw new InvalidLottoException("로또 범위는 6개여야 합니다.");
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

    @Override
    public String toString() {
        return "["+lottoNumbers.stream().map(LottoNumber::toString).collect(Collectors.joining(","))+"]";
    }

}
