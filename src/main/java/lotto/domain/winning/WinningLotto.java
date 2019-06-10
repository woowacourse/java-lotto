package lotto.domain.winning;

import lotto.domain.lottomanager.LottoCreator;
import lotto.domain.lottomanager.LottoNumber;
import lotto.domain.lottomanager.LottoTicket;
import lotto.domain.result.Rank;

import java.util.List;

public class WinningLotto {
    private static final String ERROR_NULL_WINNING_NUMBERS = "createWinningLotto() has Null";
    private static final String ERROR_OVERLAPPED_WINNING_NUMBERS = "당첨 번호에 동일한 수가 존재합니다.";
    private static final String ERROR_NULL_USER_TICKET = "getMatchRank() has Null";

    private LottoTicket winningLotto;
    private LottoNumber bonusBall;

    private WinningLotto(LottoTicket winningLotto, LottoNumber bonusBall) {
        this.winningLotto = winningLotto;
        this.bonusBall = bonusBall;
    }

    public static WinningLotto createWinningLotto(List<Integer> winningNumbers, Integer bonusBall) {
        winningNumbers.forEach(WinningLotto::checkNullNumber);
        checkOverlapWithWinningNumbers(bonusBall, winningNumbers);

        return new WinningLotto(LottoCreator.createManualTickets(winningNumbers), LottoNumber.createLottoNumber(bonusBall));
    }

    private static void checkNullNumber(Integer number) {
        if (number == null) {
            throw new NullPointerException(ERROR_NULL_WINNING_NUMBERS);
        }
    }

    private static void checkOverlapWithWinningNumbers(Integer bonusBall, List<Integer> winningLotto) {
        if (winningLotto.contains(bonusBall)) {
            throw new IllegalArgumentException(ERROR_OVERLAPPED_WINNING_NUMBERS);
        }
    }

    public Rank getMatchRank(LottoTicket userTicket) {
        if (userTicket == null) {
            throw new IllegalArgumentException(ERROR_NULL_USER_TICKET);
        }

        return Rank.valueOf(winningLotto.getMatchedNumbersCount(userTicket), userTicket.isContainBonus(bonusBall));
    }
}
