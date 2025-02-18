package model;

import static constant.LottoConstant.LOTTO_NUMBER_COUNT;

import java.util.Set;
import java.util.TreeSet;

public record Lotto(
        Set<LottoNumber> lottoNumbers
) {
    public Lotto {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    public boolean containsNumber(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public Set<LottoNumber> getLottoNumbers() {
        return new TreeSet<>(lottoNumbers);
    }
}
