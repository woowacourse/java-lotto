package lotto.domain;

import java.util.*;

public class LottoTicket {

    private static final int VALID_NUMBER_COUNT = 6;
    private static final String NULL_ERROR_MESSAGE = "null값은 허용하지 않습니다.";
    private static final String EMPTY_ERROR_MESSAGE = "숫자는 하나 이상이어야 합니다.";
    private static final String COUNT_ERROR_MESSAGE = "숫자는 6개여야 합니다.";
    private static final String DUPLICATE_ERROR_MESSAGE = "중복된 숫자가 존재합니다.";

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        Objects.requireNonNull(lottoNumbers, NULL_ERROR_MESSAGE);
        validateEmptyTicket(lottoNumbers);
        validateCount(lottoNumbers);
        validateDuplicate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    private void validateEmptyTicket(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
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
}
