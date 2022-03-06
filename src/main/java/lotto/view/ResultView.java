package lotto.view;

import lotto.model.Lotto;
import lotto.model.LottoResult;
import lotto.model.Rank;

import java.util.List;

public class ResultView {

    public static void printBuyingLotto(List<Lotto> lottos, int manualLottoSize) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.", manualLottoSize, lottos.size() - manualLottoSize);
        System.out.println();
        lottos.stream()
                .map(Lotto::toIntegers)
                .forEach(System.out::println);
        printEmptyLine();
    }

    public static void printResultStatistics(LottoResult lottoResult) {
        printEmptyLine();
        System.out.println("당첨 통계");
        System.out.println("---------");
        Rank.getWinningRanks()
                .stream()
                .map(rank -> getOneRankStatus(rank, lottoResult.getRankCount(rank)))
                .forEach(System.out::println);
    }

    private static String getOneRankStatus(Rank rank, Integer winningCount) {
        if (rank == Rank.SECOND) {
            return String.format("%d개 일치, 보너스 볼 일치(%d원)- %d개", rank.getCorrectedBall(), rank.getMoney(), winningCount);
        }
        return String.format("%d개 일치 (%d원)- %d개", rank.getCorrectedBall(), rank.getMoney(), winningCount);
    }

    public static void printYield(float yield) {
        System.out.println(getYieldStatus(yield));
    }

    private static String getYieldStatus(float yield) {
        if (yield >= 1) {
            return String.format("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 이득이라는 의미임)", yield);
        }
        return String.format("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", yield);
    }

    private static void printEmptyLine() {
        System.out.println();
    }
}
