package lottogame.domain.number;

import java.util.ArrayList;
import java.util.Collections;
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

    public boolean contains(final LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }


    public List<LottoNumber> toList() {
        return Collections.unmodifiableList(new ArrayList<>(this.lottoNumbers));
    }
}
