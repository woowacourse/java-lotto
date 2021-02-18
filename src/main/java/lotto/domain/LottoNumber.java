package lotto.domain;

import static lotto.domain.LottoNumberGenerator.MAXIMUM_CANDIDATE_NUMBER;
import static lotto.domain.LottoNumberGenerator.MINIMUM_CANDIDATE_NUMBER;

public class LottoNumber {
    public static final String NUMBER_OUT_OF_BOUNDS_ERROR = "1 에서 45 사이의 숫자만 입력해주세요.";

    private final int number;

    public LottoNumber(int number) {
        validateNumberBetween1to45(number);
        this.number = number;
    }

    private void validateNumberBetween1to45(int number) {
        if (number < MINIMUM_CANDIDATE_NUMBER || MAXIMUM_CANDIDATE_NUMBER < number) {
            throw new IllegalArgumentException(NUMBER_OUT_OF_BOUNDS_ERROR);
        }
    }
}
