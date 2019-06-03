package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public abstract class Lotto {

    public List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        if (isDuplicated(lottoNumbers)) {
            throw new InvalidLottoException("중복된 수가 있습니다.");
        }
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
        Collections.sort(this.lottoNumbers);
    }

    boolean isDuplicated(List<LottoNumber> scannedNumbers) {
        return scannedNumbers.size() != new HashSet<>(scannedNumbers).size();
    }

    abstract boolean isValidSize(List<LottoNumber> scannedNumbers);

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
