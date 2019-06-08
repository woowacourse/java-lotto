package lotto.domain.winning;

import lotto.domain.lottomanager.LottoNumber;
import lotto.domain.lottomanager.LottoTicket;
import lotto.domain.lottomanager.LottoCreator;

import java.util.List;

public class WinningLotto {
    private static final String ERROR_NULL_WINNING_NUMBERS = "createWinningLotto() has Null";
    private static final String ERROR_NULL_BONUS_NUMBER = "isContainedWinningNumbers() has Null";
    private static final String ERROR_NULL_LOTTO_TICKET = "getMatchedWinningNumbersCount() has Null";
    private LottoTicket winningLotto;

    private WinningLotto(LottoTicket winningLotto) {
        this.winningLotto = winningLotto;
    }

    public static WinningLotto createWinningLotto(List<Integer> winningNumbers) {
        winningNumbers.forEach(WinningLotto::checkNullNumber);

        return new WinningLotto(LottoCreator.createManualTickets(winningNumbers));
    }

    private static void checkNullNumber(Integer number) {
        if (number == null) {
            throw new NullPointerException(ERROR_NULL_WINNING_NUMBERS);
        }
    }

    public int getMatchedWinningNumbersCount(LottoTicket lottoTicket) {
        if (lottoTicket == null) {
            throw new NullPointerException(ERROR_NULL_LOTTO_TICKET);
        }

        return winningLotto.getMatchedNumbersCount(lottoTicket);
    }

    boolean isContainedWinningNumbers(LottoNumber bonusBall) {
        if (bonusBall == null) {
            throw new NullPointerException(ERROR_NULL_BONUS_NUMBER);
        }

        return winningLotto.isContainedNumbers(bonusBall);
    }
}
