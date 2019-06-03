package lotto.view;

import lotto.domain.Lotteries;
import lotto.domain.Lotto;
import lotto.domain.Rank;

import java.util.Collections;
import java.util.Map;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class OutputView {
    private static final String OUTPUT_BUY_LOTTO_COUNT = "수동으로 %d장, 자동으로 %d장을 구매하셨습니다.\n";
    private static final String TITLE_INPUT_AUTO_LOTTO = "수동으로 구매할 번호를 입력해 주세요.";
    public static final String START_SYMBOL = "[";
    public static final String END_SYMBOL = "]";
    public static final String SEPARATOR = ",";

    public static void outputUserBuyLottoCount(long manualCount, long autoCount) {
        System.out.printf(OUTPUT_BUY_LOTTO_COUNT, manualCount, autoCount);
    }

    public static void titleInputAutoLotto() {
        System.out.println(TITLE_INPUT_AUTO_LOTTO);
    }

    public static void outputAutoLotteries(Lotteries lotteries) {
        for (Lotto lotto : lotteries) {
            System.out.println(lotto.toStringWithFormat(START_SYMBOL, END_SYMBOL, SEPARATOR));
        }
    }

    public static void outputLotteriesResult(Map<Rank, Integer> rankResult) {
        System.out.println("\n당첨 통계\n---------");
        for (Map.Entry<Rank, Integer> rankIntegerEntry : rankResult.entrySet()) {
            generateResultSentence(rankIntegerEntry.getKey(), rankIntegerEntry.getValue());
        }
    }

    private static void generateResultSentence(Rank rank, int matchCount) {
        if (rank.equals(Rank.MISS)) {
            return;
        }
        if (rank.equals(Rank.SECOND)) {
            System.out.println(String.format("%d개 일치, 보너스 볼 일치(%d원) - %d개", rank.getCountOfMatch(), rank.getWinningMoney(), matchCount));
            return;
        }
        System.out.println(String.format("%d개 일치 (%d)- %d개", rank.getCountOfMatch(), rank.getWinningMoney(), matchCount));
    }

    public static void outputLotteriesRate(float rate) {
        System.out.println(String.format("총 수익률은 %.3f입니다.", rate));
    }
}
