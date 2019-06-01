package lotto.utils;

import lotto.domain.lottofactory.LottoNumber;
import lotto.domain.lottofactory.LottoTicket;

import java.util.List;

public class NullCheckUtil {
    private static final String ERROR_NULL = "null error";

    public static void checkNullInput(String input) {
        if (input == null) {
            throw new NullPointerException(ERROR_NULL);
        }
    }

    public static void checkNullInteger(Integer number) {
        if (number == null) {
            throw new NullPointerException(ERROR_NULL);
        }
    }

    private static void checkNullLottoNumber(LottoNumber number) {
        if (number == null) {
            throw new NullPointerException(ERROR_NULL);
        }
    }

    public static void checkNullLottoTicket(LottoTicket ticket) {
        if (ticket == null) {
            throw new NullPointerException(ERROR_NULL);
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

    public static void checkNullWinningNumbers(List<Integer> winningNumbers) {
        for (Integer number : winningNumbers) {
            checkNullInteger(number);
        }
    }
}
