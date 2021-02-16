package lotto;

import java.util.Objects;
import java.util.Set;

public class LottoTicket {
    private static final int VALID_NUMBER_COUNT = 6;
    private static final String COUNT_ERROR_MESSAGE = "숫자는 6개여야 합니다.";

    private final Set<LottoNumber> lottoNumbers;

    public LottoTicket(Set<LottoNumber> lottoNumbers) {
        validateCount(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public void validateCount(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() == VALID_NUMBER_COUNT) {
            return;
        }
        throw new RuntimeException(COUNT_ERROR_MESSAGE);
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
