package lotto.domain.number;

import lotto.domain.exception.LottoNumberSizeException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.LinkedHashSet;


public class LottoRound {
    public static final int LOTTO_NUMBER_AMOUNT = 6;
    private static final String LOTTO_NUMBER_AMOUNT_EXCEPTION_MESSAGE = "로또 번호의 갯수가 6개가 아닙니다.";

    private final Set<LottoNumber> lottoNumbers;

    public LottoRound(List<LottoNumber> randomNumbers) {
        Objects.requireNonNull(randomNumbers);
        validateSize(randomNumbers);
        Collections.sort(randomNumbers);
        this.lottoNumbers = new LinkedHashSet<>(randomNumbers);
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_AMOUNT) {
            throw new LottoNumberSizeException(LOTTO_NUMBER_AMOUNT_EXCEPTION_MESSAGE);
        }
    }

    public boolean has(LottoNumber lottoNumber) {
        return this.lottoNumbers.stream()
                .anyMatch(containsLottoNumber -> containsLottoNumber.equals(lottoNumber));
    }

    public int calculateCorrectNumberCount(LottoRound lottoRound) {
        return (int) this.lottoNumbers.stream()
                .filter(lottoRound::has)
                .count();
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }

}
