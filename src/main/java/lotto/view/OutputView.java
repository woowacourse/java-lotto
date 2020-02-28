package lotto.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.*;

public class OutputView {

    public static void printRetryRequestWithMessage(String message) {
        System.out.println(String.format("다음과 같은 이유로 다시 시도해 주세요 - %s", message));
    }

    public static void printLottos(List<Lotto> lottosManual, List<Lotto> lottosAutomatic) {
        System.out.println(String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.", lottosManual.size(), lottosAutomatic.size()));
        for (Lotto lottoManual : lottosManual) {
            printLotto(lottoManual);
        }

        for (Lotto lottoAutomatic : lottosAutomatic) {
            printLotto(lottoAutomatic);
        }
    }

    public static void printResult(Result result) {
        System.out.println("당첨 통계\n---------");
        printStatistics(result.getWinningRanks());
        printEarningRate(result.getEarningRate());
    }

    private static void printEarningRate(int earningRate) {
        System.out.println(String.format("총 수익률은 %d%%입니다.", earningRate));
    }

    private static void printStatistics(WinningRanks winningRanks) {
        Map<Rank, Integer> winningResults = winningRanks.getWinningRanks();
        for (Rank rank : winningResults.keySet()) {
            String resultMessage = String.format("%d개 일치(%d원)- %d개", rank.getMatchNumber(),
                    rank.calculateWinningMoney().getValue(), winningResults.get(rank));
            System.out.println(resultMessage);
        }
    }

    public static void printLotto(Lotto lotto) {
        String lottoString = lotto.getLottoNumbers().stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(",", "[", "]"));

        System.out.println(lottoString);
    }

}
