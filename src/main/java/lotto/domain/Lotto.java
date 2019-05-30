package lotto.domain;

import lotto.domain.domainexception.InvalidLottoException;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static lotto.domain.domainconstants.DomainConstants.LOTTO_SIZE;

public class Lotto {
    private static final String DELIMITER = ", ";
    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";

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

    @Override
    public String toString() {
        return OPEN_BRACKET + lottoNumbers.stream().map(LottoNumber::toString).collect(Collectors.joining(DELIMITER)) + CLOSE_BRACKET;
    }
}
