package lotto.model;

import lotto.exception.NotSixNumbersException;
import lotto.exception.OverRangeException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTicket {
    private static final int LAST_LOTTO_NUMBER = 45;
    private static final int FIRST_LOTTO_NUMBER = 1;
    private static final int FIRST_INDEX = 0;
    protected static final int LOTTO_NUMBER_LENGTH = 6;
    protected static final String OVER_LOTTO_NUMBER_RANGE_EXCEPTION_MESSAGE = "1 ~ 45 범위를 벗어났습니다.";
    protected final String LOTTO_NUMBER_EXCEPTION_MESSAGE = "6개의 숫자를 입력하셔야 합니다.";
    private List<Integer> lottoTicket = new ArrayList<>();

    public LottoTicket() {
        for (int i = FIRST_LOTTO_NUMBER; i <= LAST_LOTTO_NUMBER; i++) {
            lottoTicket.add(i);
        }
        Collections.shuffle(lottoTicket);
        lottoTicket = lottoTicket.subList(FIRST_INDEX, LOTTO_NUMBER_LENGTH);
        Collections.sort(lottoTicket);
    }

    // 수동
    public LottoTicket(List<Integer> lottoTicket) {
        checkLottoLength(lottoTicket);
        for (int number : lottoTicket) {
            checkLottoNumberRange(number);
        }
        this.lottoTicket = lottoTicket;
    }

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
}
