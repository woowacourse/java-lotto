package lotto.domain;

import lotto.domain.exception.InvalidLottoException;

import java.util.*;

import static java.util.stream.Collectors.*;

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
            throw new InvalidLottoException(lottoNumbers.size());
        }
        if (checkValidateNumber.size() != lottoNumbers.size()) {
            throw new InvalidLottoException(checkValidateNumber.size(), lottoNumbers.size());
        }

        return lottoNumbers;
    }

    protected int numberOfMatch(List<LottoNumber> winningLotto) {
        return (int) lottoNumbers.stream()
                .filter(winningLotto::contains)
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
                .collect(joining(DELIMITER))
                + END_BRACKET;
    }

}
