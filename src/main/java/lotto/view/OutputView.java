package lotto.view;

import lotto.domain.*;

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

    public static void printResults(ResultsDTO resultsDTO) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        printWinningResults(resultsDTO);
        printEarningRate(resultsDTO);
    }

    public static void printWinningResults(ResultsDTO resultsDTO) {
        for (WinningInfo winningInfo : WinningInfo.values()) {
            if (winningInfo == WinningInfo.FAIL) {
                continue;
            }
            int winningCount = resultsDTO.getWinningCount(winningInfo);
            printWinnigInfo(winningInfo);
            System.out.println(winningCount+"개");
        }
    }

    public static void printEarningRate(ResultsDTO resultsDTO) {
        String earningRate = String.format("총 수익률은 %s%%입니다.", resultsDTO.getEarningRate());
        System.out.println(earningRate);
    }

    public static void printWinnigInfo(WinningInfo winningInfo) {
        String winningCount = String.format("%d 개 일치", winningInfo.getWinningCount());
        String winningPrice = String.format("(%d원) - ", winningInfo.getWinningPrice());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(winningCount);
        if (winningInfo.getHasBonus()) {
            stringBuilder.append(", 보너스 볼 일치");
        }
        stringBuilder.append(winningPrice);
        System.out.print(stringBuilder);
    }
}
