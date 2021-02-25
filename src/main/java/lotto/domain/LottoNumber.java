package lotto.domain;

import lotto.exception.LottoCustomException;

public class LottoNumber {

    public static final int LOTTO_NUMBER_MAX_LIMIT = 45;
    public static final int LOTTO_NUMBER_MIN_LIMIT = 1;
    private static final String NOT_PROPER_RANGE_NUMBER_ERROR_MESSAGE = "로또 번호는 %d-%d 사이의 숫자이어야 합니다.";
    private static final String DUPLICATE_NUMBERS_BY_BONUS_BALL_ERROR_MESSAGE = "보너스 볼은 지난 주 당첨번호와 중복될 수 없습니다.";

    private final int number;

    public LottoNumber(final int number) {
        if (isNotProperRange(number)) {
            throw new LottoCustomException(String
                .format(NOT_PROPER_RANGE_NUMBER_ERROR_MESSAGE, LOTTO_NUMBER_MIN_LIMIT,
                    LOTTO_NUMBER_MAX_LIMIT));
        }
        this.number = number;
    }

    private boolean isNotProperRange(int number) {
        return number < LOTTO_NUMBER_MIN_LIMIT || number > LOTTO_NUMBER_MAX_LIMIT;
    }

    public void validateDuplicate(LottoTicket lottoTicket) {
        if (lottoTicket.hasNumber(this)) {
            throw new LottoCustomException(DUPLICATE_NUMBERS_BY_BONUS_BALL_ERROR_MESSAGE);
        }
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber lottoNumber = (LottoNumber) o;
        return number == lottoNumber.number;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(number);
    }
}
