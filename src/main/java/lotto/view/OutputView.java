package lotto.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.*;

public class OutputView {

    public static void printLottosSize(int lottosSize) {
        System.out.println(String.format("%d개를 구매했습니다.", lottosSize));
    }

    public static void printLottos(List<Lotto> lottosManual, List<Lotto> lottosAutomatic) {
        System.out.println(String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.", lottosManual.size(), lottosAutomatic.size()));
        for (Lotto lottoManual : lottosManual) {
            printLoto(lottoManual);
        }

        for (Lotto lottoAutomatic : lottosAutomatic) {
            printLoto(lottoAutomatic);
        }
    }

    private static void printLoto(Lotto lotto) {
        List<String> stringifiedLottoNumbers = lotto.getLottoNumbers().stream()
                .map(LottoNumber::toString)
                .collect(Collectors.toList());

        System.out.println("[" + String.join(",", stringifiedLottoNumbers) + "]");

    }

    public static void printEarningRate(int earningRate) {
        System.out.println(String.format("총 수익률은 %d%%입니다.", earningRate));
    }

    public static void printResult(Result result) {
        System.out.println("당첨 통계\n---------");
        printStatistics(result.getWinningRanks());
        printEarningRate(result.getEarningRate());
    }

    private static void printStatistics(WinningRanks winningRanks) {
        Map<Rank, Integer> winningResults = winningRanks.getWinningRanks();
        for (Rank rank : winningResults.keySet()) {
            System.out.println(
                    String.format("%d개 일치(%d원)- %d개", rank.getMatchNumber(), rank.calculateWinningMoney().getValue(),
                            winningResults.get(rank)));
        }
    }
}
