package lotto.view;

import java.util.Arrays;
import java.util.List;

import lotto.model.Lotto;
import lotto.model.LottoResult;
import lotto.model.Rank;
import lotto.model.Yield;

public class ResultView {
    public static void printGeneratedLottos(List<Lotto> manualLottos, List<Lotto> lottos) {
        System.out.println("수동으로 " + manualLottos.size() + "장, 자동으로 " + lottos.size() + "개를 구매했습니다.");
        printEachLottos(manualLottos);
        printEachLottos(lottos);
        printEmptyLine();
    }

    private static void printEachLottos(List<Lotto> lottos) {
        lottos.stream()
            .map(Lotto::toIntegers)
            .forEach(System.out::println);
    }

    public static void printResultStatistics(LottoResult lottoResult) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        Arrays.stream(Rank.values())
            .filter(rank -> rank != Rank.FAIL)
            .map(rank -> getOneRankStatus(rank, lottoResult.getRankCount(rank)))
            .forEach(System.out::println);
    }

    private static String getOneRankStatus(Rank rank, Long winningCount) {
        if (rank == Rank.SECOND) {
            return String.format("%d개 일치, 보너스 볼 일치(%d원)- %d개", rank.getMatchScore(), rank.getMoney(), winningCount);
        }
        return String.format("%d개 일치 (%d원)- %d개", rank.getMatchScore(), rank.getMoney(), winningCount);
    }

    public static void printYield(Yield yield) {
        System.out.println(getYieldStatus(yield));
    }

    private static String getYieldStatus(Yield yield) {
        if (yield.isGain()) {
            return String.format("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 이득이라는 의미임)", yield.getYield());
        }
        return String.format("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", yield.getYield());
    }

    private static void printEmptyLine() {
        System.out.println();
    }
}
