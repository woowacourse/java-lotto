package lotto.view;

import lotto.domain.Lottos;
import lotto.domain.PaidPrice;
import lotto.domain.Results;
import lotto.domain.WinningInfo;

public class OutputView {
    public static void printLottoCount(PaidPrice paidPrice) {
        System.out.println(paidPrice.getLottoCount() + "개를 구매했습니다");
    }

    public static void printLottiesNumbers(Lottos lottos) {
        int lottosLength = lottos.getLottos().size();
        for (int i = 0; i < lottosLength; i++) {
            System.out.println(lottos.getLottoByIndex(i).getLottoNumbers().toString());
        }
    }

    public static void printResults(Results results) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (WinningInfo winningInfo : WinningInfo.values()) {
            int winningCount = results.getWinningCount(winningInfo);
            String result = String.format("%s - %s개",
                    winningInfo.toString(), winningCount);
            System.out.println(result);
        }

        String earningRate = String.format("총 수익률은 %s%%입니다.", results.getEarningRate());
        System.out.println(earningRate);
    }
}
