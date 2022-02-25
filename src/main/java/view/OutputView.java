package view;

import domain.Lotto;
import domain.RankPrice;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;

public class OutputView {

    private static final String INPUT_MONEY_INSTRUCTION = "구입금액을 입력해 주세요.";
    private static final String PURCHASE_MESSAGE = "개를 구매했습니다.";
    private static final String INPUT_WIN_LOTTO_INSTRUCTION = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_INSTRUCTION = "보너스 볼을 입력해 주세요.";
    private static final String WIN_STATISTICS_RESULT_MESSAGE = "당첨 통계\n---------";
    private static final String SECOND_RANK_CORRECT_MESSAGE = "개 일치, 보너스 볼 일치(";
    private static final String RANK_PRICE_MESSAGE = "원)- ";
    private static final String RANK_COUNT_MESSAGE = "개";
    private static final String RANK_CORRECT_MESSAGE = "개 일치 (";
    private static final String WIN_PROFIT_RESULT_MESSAGE = "총 수익률은 %.2f입니다. (기준이 1 이기 때문에 결과적으로 손해라는 의미임)";

    public static void printMoneyInstruction() {
        System.out.println(INPUT_MONEY_INSTRUCTION);
    }

    public static void printLotto(final List<Lotto> issuedLotto) {
        System.out.println(issuedLotto.size() + PURCHASE_MESSAGE);
        for (Lotto lotto : issuedLotto) {
            System.out.println(lotto.getLotto());
        }
    }

    public static void printWinLottoInstruction() {
        System.out.println();
        System.out.println(INPUT_WIN_LOTTO_INSTRUCTION);
    }

    public static void printBonusInstruction() {
        System.out.println();
        System.out.println(INPUT_BONUS_INSTRUCTION);
    }

    public static void printWinStatistics(final SortedMap<RankPrice, Integer> result) {
        System.out.println();
        System.out.println(WIN_STATISTICS_RESULT_MESSAGE);
        for (Entry<RankPrice, Integer> rankCount : result.entrySet()) {
            printWinStatistics(rankCount);
        }
    }

    private static void printWinStatistics(final Entry<RankPrice, Integer> rankCount) {
        final RankPrice rankPrice = rankCount.getKey();
        if (rankPrice == RankPrice.SECOND) {
            System.out.println(
                    rankPrice.getCount() + SECOND_RANK_CORRECT_MESSAGE + rankPrice.getPrice()
                            + RANK_PRICE_MESSAGE
                            + rankCount.getValue()
                            + RANK_COUNT_MESSAGE);
            return;
        }
        System.out.println(
                rankPrice.getCount() + RANK_CORRECT_MESSAGE + rankPrice.getPrice() + RANK_PRICE_MESSAGE
                        + rankCount.getValue() + RANK_COUNT_MESSAGE);
    }

    public static void printWinProfit(final double calculateProfit) {
        System.out.println(String.format(WIN_PROFIT_RESULT_MESSAGE, calculateProfit));
    }
}
