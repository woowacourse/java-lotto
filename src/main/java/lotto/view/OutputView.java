package lotto.view;

import lotto.domain.*;

import java.util.Arrays;
import java.util.stream.Collectors;

public class OutputView {
    public static final String SPLIT_DELIMETER = ",";

    public static void printLottoSize(int manaulLottoSize, int autoLottoSize) {
        String result = "수동으로 %s장, 자동으로 %s개를 구매했습니다.";
        System.out.println(String.format(result, manaulLottoSize, autoLottoSize));
    }

    public static void printLottosNumbers(Lottos lottos) {
        int lottosLength = lottos.getLottos().size();
        for (int i = 0; i < lottosLength; i++) {
            System.out.println(lottos.getLottoNumbers(i));
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
        for (WinningInfo winningInfo : WinningInfo.getValuesWithoutFail()) {
            int winningCount = resultsDTO.getMatchCount(winningInfo);
            printWinnigInfo(winningInfo);
            System.out.println(winningCount + "개");
        }
    }

    public static void printEarningRate(ResultsDTO resultsDTO) {
        String earningRate = String.format("총 수익률은 %s%%입니다.", resultsDTO.getEarningRate());
        System.out.println(earningRate);
    }

    public static void printWinnigInfo(WinningInfo winningInfo) {
        String winningCount = String.format("%d 개 일치", winningInfo.getMatchCount());
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
