package lotto.view;

import lotto.domain.Lotteries;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.RankResult;

import java.util.Map;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class OutputView {
    private static final String OUTPUT_BUY_LOTTO_COUNT = "수동으로 %d장, 자동으로 %d장을 구매하셨습니다.\n";
    private static final String TITLE_INPUT_AUTO_LOTTO = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String START_SYMBOL = "[";
    private static final String END_SYMBOL = "]";
    private static final String SEPARATOR = ",";
    private static final String TITLE_RATE_OF_JACKPOT = "\n당첨 통계\n---------";
    private static final String OUTPUT_SECOND_RANK = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String OUTPUT_DEFALUT_RANK = "%d개 일치 (%d)- %d개";
    private static final String OUTPUT_RATE = "총 수익률은 %.0f%s입니다.";

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

    public static void outputLotteriesResult(RankResult rankResult) {
        outputLotteriesRank(rankResult.getRankResult());
        outputLotteriesRate(rankResult.getRate());

    }

    private static void outputLotteriesRank(Map<Rank, Integer> rankResult) {
        System.out.println(TITLE_RATE_OF_JACKPOT);
        for (Map.Entry<Rank, Integer> rankIntegerEntry : rankResult.entrySet()) {
            generateResultSentence(rankIntegerEntry.getKey(), rankIntegerEntry.getValue());
        }
    }

    private static void outputLotteriesRate(float rate) {
        System.out.println(String.format(OUTPUT_RATE, rate, "%"));
    }

    private static void generateResultSentence(Rank rank, int matchCount) {
        if (rank.equals(Rank.MISS)) {
            return;
        }
        if (rank.equals(Rank.SECOND)) {
            System.out.println(String.format(OUTPUT_SECOND_RANK, rank.getCountOfMatch(), rank.getWinningMoney(), matchCount));
            return;
        }
        System.out.println(String.format(OUTPUT_DEFALUT_RANK, rank.getCountOfMatch(), rank.getWinningMoney(), matchCount));
    }
}
