package view;

import static utils.Messages.*;

import domain.LottoNumber;
import domain.LottoTicket;
import domain.LottoTickets;
import domain.Purchase;
import domain.Rank;
import domain.Result;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String LOTTO_PREFIX = "[";
    private static final String LOTTO_ENDFIX = "]";
    private static final String SEPARATOR = ", ";

    public static void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public static void printLottoTickets(Purchase purchase, LottoTickets lottoTickets) {
        System.out.printf(BUY_MESSAGE, purchase.getManualCount(), purchase.getAutoCount());
        for (LottoTicket lottoTicket : lottoTickets.get()) {
            printLottoTicket(lottoTicket);
        }
    }

    private static void printLottoTicket(LottoTicket lottoTicket) {
        List<String> LottoNumbers = lottoTicket.get().stream()
            .map(lottoNumber -> Integer.toString(lottoNumber.get()))
            .collect(Collectors.toList());

        String result = LOTTO_PREFIX + String.join(SEPARATOR, LottoNumbers) + LOTTO_ENDFIX;
        System.out.println(result);
    }

    public static void printResult(Result result) {
        System.out.println(RESULT_START_MESSAGE);
        for (Rank rank : Rank.getWithoutDefault()) {
            System.out.printf(RESULT_RANK_MESSAGE,
                rank.getMatchCount(), printIfSecond(rank),
                rank.getPrizeMoney(), result.getRankCount(rank));
        }
    }

    private static String printIfSecond(Rank rank) {
        if (rank.equals(Rank.SECOND)) {
            return SAME_BONUS_MESSAGE;
        }
        return " ";
    }

    public static void printProfit(float profit) {
        System.out.printf(PROFIT_MESSAGE,
            profit, printIfLoss(profit));
    }

    private static Object printIfLoss(float profit) {
        if (profit >= 1) {
            return NO_MESSAGE;
        }
        return "";
    }
}