package lotto.domain;

import lotto.domain.domainexception.InvalidLottoException;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private static final String DELIMITER = ", ";
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";

    private List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validLotto(lottoNumbers);
        Collections.sort(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validLotto(List<LottoNumber> lotto) {
        checkLottoSize(lotto);
        checkSameLottoNumber(lotto);
    }

    private void checkLottoSize(List<LottoNumber> lotto) {
        if (lotto.size() != LOTTO_SIZE) {
            throw new InvalidLottoException("로또는 1장에 6개의 숫자로 이루어져 있습니다.");
        }
    }

    private void checkSameLottoNumber(List<LottoNumber> lotto) {
        if (lotto.size() != new HashSet<>(lotto).size()) {
            throw new InvalidLottoException("로또에 중복되는 숫자가 있습니다.");
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

    @Override
    public String toString() {
        return OPEN_BRACKET + lottoNumbers.stream().map(LottoNumber::toString).collect(Collectors.joining(DELIMITER)) + CLOSE_BRACKET;
    }
}
