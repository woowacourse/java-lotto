package view;

import domain.LottoBall;
import domain.Lotto;
import domain.Rank;
import domain.Result;
import java.util.List;

public class OutputView {

    public static final String BUY_MESSAGE = "개를 구매했습니다.";
    public static final String LOTTO_PREFIX = "[";
    public static final String LOTTO_ENDFIX = "]";
    public static final String SEPARATOR = ", ";
    public static final int DELETE_IDX = 2;
    public static final String RESULT_START_MESSAGE = "당첨 통계" + System.lineSeparator() + "---------";
    public static final String RESULT_RANK_MESSAGE = "%d개 일치%s(%d원)- %d개" + System.lineSeparator();
    public static final String SAME_BONUS_MESSAGE = ", 보너스 볼 일치";
    public static final String PROFIT_MESSAGE =
            "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해%s라는 의미임)" + System.lineSeparator();
    public static final String NO_MESSAGE = " 아니";

    public static void printLottoTickets(List<Lotto> lottoTickets) {
        System.out.println(lottoTickets.size()+ BUY_MESSAGE);
        for (Lotto lotto : lottoTickets) {
            printLottoNumbers(lotto);
        }
    }

    private static void printLottoNumbers(Lotto lotto) {
        StringBuilder result = new StringBuilder(LOTTO_PREFIX);
        for (LottoBall lottoBall : lotto.get()) {
            result.append(lottoBall.get()).append(SEPARATOR);
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