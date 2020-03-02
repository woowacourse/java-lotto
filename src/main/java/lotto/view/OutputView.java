package lotto.view;

import lotto.domain.lottoTicket.Lotto;
import lotto.domain.lottoTicket.LottoAmount;
import lotto.domain.result.WinningValue;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String LOTTO_AMOUNT_MESSAGE = "수동으로 %d장, 자동으로 %d장을 구매했습니다.";
    private static final String WINNING_ANALYSIS_MESSAGE = "\n당첨 통계\n" + "---------";

    public static void printLottoAmount(LottoAmount lottoAmount) {
        System.out.println(
                String.format(LOTTO_AMOUNT_MESSAGE,
                        lottoAmount.getManualLottoAmount(),
                        lottoAmount.getAutoLottoAmount())
        );
    }

    public static void printLottoNumbers(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
        System.out.println();
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
