package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.WinningValue;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String WINNING_ANALYSIS_MESSAGE = "당첨 통계\n" + "---------";

    public static void printPurchaseCount(int lottoTicketCount) {
        System.out.println(lottoTicketCount + "개를 구매했습니다.");
    }

    public static void printLottoNumbers(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getLotto());
        }
    }

    public static void printLottoResults(Map<WinningValue, Integer> lottoResult) {
        System.out.println(WINNING_ANALYSIS_MESSAGE);
        lottoResult.entrySet().forEach(OutputView::printLottoResult);

    }

    private static void printLottoResult(Map.Entry<WinningValue, Integer> result) {
        System.out.print(result.getKey().getMessage());
        System.out.print("(" + result.getKey().getReward() + "원)- ");
        System.out.println(result.getValue() + "개");
    }

    public static void printRewardRate(int rewardRate) {
        System.out.println("총 수익률은 " + rewardRate + "% 입니다.");
    }
}
