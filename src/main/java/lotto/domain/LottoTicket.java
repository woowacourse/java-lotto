package lotto.domain;

import lotto.domain.errors.ErrorMessage;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoTicket {
    private static final int LOTTO_NUMBER_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(final List<LottoNumber> lottoNumbers) {
        validateNumberCount(lottoNumbers);
        validateDistinctNumbers(lottoNumbers);
        Collections.sort(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public void validateNumberCount(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
            ErrorMessage nowErrorMessage = ErrorMessage.NUMBER_COUNT_NOT_SIX;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }

    public void validateDistinctNumbers(final List<LottoNumber> inputNumbers) {
        if (getDistinctSize(inputNumbers) != LOTTO_NUMBER_SIZE) {
            ErrorMessage nowErrorMessage = ErrorMessage.DUPLICATE_NUMBER;
            throw new IllegalArgumentException(nowErrorMessage.getMessage());
        }
    }

    private long getDistinctSize(final List<LottoNumber> inputNumbers) {
        return inputNumbers.stream()
                .mapToInt(LottoNumber::getNumber)
                .distinct()
                .count();
    }

    public final List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
