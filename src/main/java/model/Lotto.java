package model;

import static constant.LottoConstant.LOTTO_NUMBER_COUNT;

import constant.LottoConstant;
import java.util.Set;
import java.util.TreeSet;

public class Lotto {
    private final Set<Number> lottoNumbers;

    public Lotto(Set<Number> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        this.lottoNumbers = new TreeSet<>(lottoNumbers);
    }

    public boolean containsNumber(Number number) {
        return lottoNumbers.contains(number);
    }

    public Set<Number> getLottoNumbers() {
        return new TreeSet<>(lottoNumbers);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Lotto(this.lottoNumbers);
    }
}
