package lotto.domain;

import lotto.domain.exception.InvalidLottoException;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static lotto.domain.LottoNumber.LOTTO_SIZE;

public class Lotto {
    private Set<LottoNumber> lottoNumbers;

    public Lotto(Set<LottoNumber> lottoNumbers) {
        checkLottoSize(lottoNumbers);
        this.lottoNumbers = new TreeSet<>(lottoNumbers);
    }

    private void checkLottoSize(Set<LottoNumber> lotto) {
        if (lotto.size() != LOTTO_SIZE) {
            throw new InvalidLottoException("로또는 1장에 6개의 숫자로 이루어져 있습니다.");
        }
    }

    boolean matchNumber(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    int matchNumbers(Lotto lotto) {
        return (int) lottoNumbers.stream()
                .filter(lotto::matchNumber)
                .count();
    }

    public Set<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public String toString() {
        final String numbers = lottoNumbers.stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(","));
        return numbers;
    }
}
