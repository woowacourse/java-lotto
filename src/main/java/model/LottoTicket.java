package model;

import java.util.List;

public class LottoTicket {
    private static final int LOTTO_NUMBER_COUNT = 6;
    static final String INVALID_LOTTO_NUMBER_COUNT = "[ERROR] 로또 숫자는 6개만 입력해야 합니다";
    static final String LOTTO_NUMBER_DUPLICATED = "[ERROR] 중복된 번호는 고를 수 없습니다.";

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
        validateNotDuplicatedNumber();
        validateNumberCount();
    }

    private void validateNotDuplicatedNumber() {
        long distinctSize = lottoNumbers.stream()
                .distinct().count();
        if (distinctSize != lottoNumbers.size()) {
            throw new IllegalArgumentException(LOTTO_NUMBER_DUPLICATED);
        }
    }

    private void validateNumberCount() {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_COUNT);
        }
    }
}
