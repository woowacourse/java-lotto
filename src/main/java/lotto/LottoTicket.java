package lotto;

import java.util.*;

public class LottoTicket {
    private static final int VALID_NUMBER_COUNT = 6;
    private static final String COUNT_ERROR_MESSAGE = "숫자는 6개여야 합니다.";
    private static final String DUPLICATE_ERROR_MESSAGE = "중복된 숫자가 존재합니다.";

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        validateCount(lottoNumbers);
        validateDuplicate(lottoNumbers);
        Collections.sort(lottoNumbers); //이거 위치가 어디로 가야될지?
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    private void validateDuplicate(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> uniqueLottoNumbers = new HashSet<>(lottoNumbers);
        if (uniqueLottoNumbers.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }

    private void validateCount(List<LottoNumber> lottoNumbers) {
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
