package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Lotto {

    private static final int LOTTO_SIZE = 6;
    public List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        if (isDuplicated(lottoNumbers)) {
            throw new InvalidLottoException("중복된 수가 있습니다.");
        }
        if (!isValidSize(lottoNumbers)) {
            throw new InvalidLottoException("로또 수는 6개 이어야 합니다.");
        }
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
        Collections.sort(this.lottoNumbers);
    }

    boolean isDuplicated(List<LottoNumber> scannedNumbers) {
        return scannedNumbers.size() != new HashSet<>(scannedNumbers).size();
    }

    boolean isValidSize(List<LottoNumber> scannedNumbers) {
        return scannedNumbers.size() == LOTTO_SIZE;
    }

    boolean isContains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public List<LottoNumber> getLottoNumbers() {
        return this.lottoNumbers;
    }

    public LottoNumber getLottoNumberByIndex(int index) {
        return this.lottoNumbers.get(index);
    }

    public int calculateCountOfMatch(Lotto anotherLotto) {
        return (int) lottoNumbers.stream().filter(anotherLotto.lottoNumbers::contains).count();
    }
}
