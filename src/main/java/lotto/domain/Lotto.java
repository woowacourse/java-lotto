package lotto.domain;

import java.util.*;

public class Lotto {

    private static final int LOTTO_SIZE = 6;
    public Set<LottoNumber> lottoNumbers;

    public Lotto(Set<LottoNumber> lottoNumbers) {
        if (isValidNumbers(lottoNumbers)) {
            throw new InvalidLottoException("중복되지 않은 6개의 수 이어야 합니다.");
        }
        this.lottoNumbers = new TreeSet<>(lottoNumbers);
    }

    boolean isValidNumbers(Set<LottoNumber> scannedNumbers) {
        return scannedNumbers.size() != LOTTO_SIZE;
    }

    boolean isContains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public Set<LottoNumber> getLottoNumbers() {
        return this.lottoNumbers;
    }

    public int calculateCountOfMatch(Lotto anotherLotto) {
        return (int) lottoNumbers.stream().filter(anotherLotto.lottoNumbers::contains).count();
    }
}
