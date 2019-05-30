package lotto.domain.ticket;

import lotto.domain.ticket.exception.InvalidNumberRangeException;

public class LottoNumber implements Comparable<LottoNumber> {
    private final int MAX_NUMBER = 45;
    private final int MINIMUN_NUMBER = 1;
    private final int number;

    public LottoNumber(int number) {
        validRange(number);
        this.number = number;
    }

    private void validRange(final int number) {
        if (isInvalidRange(number)) {
            throw new InvalidNumberRangeException("로또 숫자는 1이상 45 이하여야 합니다.");
        }
    }

    private boolean isInvalidRange(int number) {
        return number > MAX_NUMBER || number < MINIMUN_NUMBER;
    }

    @Override
    public int compareTo(final LottoNumber o) {
        return number - o.number;
    }
}
