package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.rule.Rank;
import lotto.model.Lotto;

public class OutputView {

    public void printLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.forEach(lotto -> System.out.println(lotto.getNumbers()));
    }

    public void printWinningStatistics(Map<Rank, Integer> ranks) {
        System.out.println("\n당첨 통계\n---------");
        Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.NO_PRIZE)
                .forEach(rank -> printWinningRankCount(rank, ranks.get(rank)));
    }

    private void printWinningRankCount(Rank rank, int count) {
        String bonusText = getBonusMatchMessage(rank);
        System.out.println(formatRankResultMessage(rank, count, bonusText));
    }

    private String getBonusMatchMessage(Rank rank) {
        if (rank.requiresBonusMatch()) {
            return ", 보너스 볼 일치";
        }
        return "";
    }

    private String formatRankResultMessage(Rank rank, int count, String bonusText) {
        return "%d개 일치%s (%d원) - %d개".formatted(rank.getMatchCount(), bonusText, rank.getWinningAmount(), count);
    }

    public void printWinningRatio(float ratio) {
        System.out.printf("총 수익률은 %.2f입니다.", ratio);
    }
}
