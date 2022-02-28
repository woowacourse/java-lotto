package view;

import static utils.Messages.*;

import domain.LottoNumber;
import domain.LottoTicket;
import domain.Rank;
import domain.Result;

import java.util.List;

public class OutputView {

    public static final String LOTTO_PREFIX = "[";
    public static final String LOTTO_ENDFIX = "]";
    public static final String SEPARATOR = ", ";
    public static final int DELETE_IDX = 2;

    public static void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public static void printLottoTickets(int manualCount, int autoCount, List<LottoTicket> lottoTickets) {
        System.out.println(MANUAL_MESSAGE+ manualCount+ AUTO_MESSAGE + autoCount + BUY_MESSAGE);
        for (LottoTicket lottoTicket : lottoTickets) {
            printLottoTicket(lottoTicket);
        }
    }

    private static void printLottoTicket(LottoTicket lottoTicket) {
        StringBuilder result = new StringBuilder(LOTTO_PREFIX);
        for (LottoNumber lottoNumber : lottoTicket.get()) {
            result.append(lottoNumber.get()).append(SEPARATOR);
        }
        result.delete(result.length() - DELETE_IDX, result.length()).append(LOTTO_ENDFIX);
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