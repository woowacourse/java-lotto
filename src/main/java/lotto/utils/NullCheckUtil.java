package lotto.utils;

import lotto.domain.result.Rank;
import lotto.domain.winning.WinningLotto;
import lotto.domain.result.WinningResult;
import lotto.domain.winning.BonusBall;
import lotto.domain.lottomanager.LottoNumber;
import lotto.domain.lottomanager.LottoTicket;
import lotto.domain.lottomanager.shufflerule.Shuffle;
import lotto.domain.user.PurchaseAmount;
import lotto.domain.user.UserTickets;

import java.util.List;

public class NullCheckUtil {
    private static final String ERROR_NULL = "null error";

    public static void checkNullInput(String input) {
        if (input == null) {
            throwNullPointerException();
        }
    }

    private static void throwNullPointerException() {
        throw new NullPointerException(ERROR_NULL);
    }

    public static void checkNullInteger(Integer number) {
        if (number == null) {
            throwNullPointerException();
        }
    }

    public static void checkNullLottoNumber(LottoNumber number) {
        if (number == null) {
            throwNullPointerException();
        }
    }

    public static void checkNullLottoTicket(LottoTicket ticket) {
        if (ticket == null) {
            throwNullPointerException();
        }
    }

    public static void checkNullLottoNumbers(List<LottoNumber> lottoNumbers) {
        for (LottoNumber lottoNumber : lottoNumbers) {
            checkNullLottoNumber(lottoNumber);
        }
    }

    public static void checkNullLottoTickets(List<LottoTicket> lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets) {
            checkNullLottoTicket(lottoTicket);
        }
    }

    public static void checkNullIntegerNumbers(List<Integer> numbers) {
        for (Integer number : numbers) {
            checkNullInteger(number);
        }
    }

    public static void checkNullBonusBall(BonusBall bonusBall) {
        if (bonusBall == null) {
            throwNullPointerException();
        }
    }

    public static void checkNullShuffle(Shuffle shuffle) {
        if (shuffle == null) {
            throwNullPointerException();
        }
    }

    public static void checkNullPurchaseAmount(PurchaseAmount purchaseAmount) {
        if (purchaseAmount == null) {
            throwNullPointerException();
        }
    }

    public static void checkNullWinningLotto(WinningLotto winningLotto) {
        if (winningLotto == null) {
            throwNullPointerException();
        }
    }

    public static void checkNullRank(Rank rank) {
        if (rank == null) {
            throwNullPointerException();
        }
    }

    public static void checkNullUserTickets(UserTickets userTickets) {
        if (userTickets == null) {
            throwNullPointerException();
        }
    }

    public static void checkNullWinningResult(WinningResult winningResult) {
        if (winningResult == null) {
            throwNullPointerException();
        }
    }
}
