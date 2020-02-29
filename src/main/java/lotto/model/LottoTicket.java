package lotto.model;

import lotto.exception.NotSixNumbersException;
import lotto.exception.OverRangeException;

import java.util.ArrayList;
import java.util.List;

public class LottoTicket {
    protected static final int FIRST_INDEX = 0;
    protected static final int FIRST_LOTTO_NUMBER = 1;
    protected static final int LAST_LOTTO_NUMBER = 45;
    protected static final int LOTTO_NUMBER_LENGTH = 6;
    protected static final String OVER_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE = "1 ~ 45 범위를 벗어났습니다.";
    protected final String LOTTO_NUMBER_EXCEPTION_MESSAGE = "6개의 숫자를 입력하셔야 합니다.";
    protected List<Integer> lottoTicket = new ArrayList<>();

    protected void checkLottoNumberRange(int number) {
        if (number < FIRST_LOTTO_NUMBER || number > LAST_LOTTO_NUMBER) {
            throw new OverRangeException(OVER_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public List<Integer> getLottoTicket() {
        return lottoTicket;
    }

    public int matchNumber(WinNumber winNumber) {
        return (int) lottoTicket.stream()
                .filter(winNumber::contains)
                .count();
    }

    public boolean matchesWithBonusBall(BonusBall bonusBall) {
        return lottoTicket.contains(bonusBall.getBonusNumber());
    }

    protected void checkLottoLength(List<Integer> inputs) {
        if (inputs.size() != LOTTO_NUMBER_LENGTH) {
            throw new NotSixNumbersException(LOTTO_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public boolean matchesWithNumber(int inputNumber) {
        return lottoTicket.contains(inputNumber);
    }
}
