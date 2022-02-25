package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbers {

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        Collections.sort(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public List<LottoNumber> get() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public boolean contains(LottoNumber bonus) {
        return lottoNumbers.contains(bonus);
    }
}