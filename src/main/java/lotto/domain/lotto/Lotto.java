package lotto.domain.lotto;

import lotto.domain.InvalidLottoException;
import lotto.domain.InvalidWinnigLotto;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Lotto {
    private static final String START_BRACKET = "[";
    private static final String END_BRACKET = "]";
    private static final String DELIMITER = ",";

    static final int NUMBER_OF_LOTTO_NUMBER = 6;
    static final int MIN_LOTTO_NUMBER = 1;
    static final int MAX_LOTTO_NUMBER = 45;

    protected List<LottoNumber> lottoNumbers;

    protected List<LottoNumber> invalidNumberOfLotto(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> checkValidateNumber = new HashSet<>(lottoNumbers);
        if (lottoNumbers.size() != NUMBER_OF_LOTTO_NUMBER) {
            throw new InvalidLottoException("로또 범위는 6개여야 합니다.");
        }
        if (checkValidateNumber.size() != lottoNumbers.size()) {
            throw new InvalidWinnigLotto("중복된 숫자가 있습니다.");
        }
        return lottoNumbers;
    }

    protected int numberOfMatch(List<LottoNumber> winningLotto) {
        return (int) lottoNumbers.stream()
                .filter(number -> {
                    if (winningLotto.contains(number)) {
                        return true;
                    }
                    return false;
                })
                .count();
    }

    protected boolean bonusOfMatch(BonusBall ball) {
        return lottoNumbers.contains(ball.getBonus());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

    @Override
    public String toString() {
        return START_BRACKET
                + lottoNumbers
                .stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(DELIMITER))
                + END_BRACKET;
    }

}
