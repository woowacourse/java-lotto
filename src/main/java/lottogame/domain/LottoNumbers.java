package lottogame.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoNumbers {

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers() {
        this(new ArrayList<>());
    }

    public LottoNumbers(final List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public void add(final LottoNumber lottoNumber) {
        lottoNumbers.add(lottoNumber);
    }

    public List<LottoNumber> toList() {
        return new ArrayList<>(this.lottoNumbers);
    }
}
