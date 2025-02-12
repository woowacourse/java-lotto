package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.controller.LottoDto;
import lotto.Rank;

public class OutputView {

    public void printLottos(List<LottoDto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.forEach(lotto ->
                System.out.println("[" + formatLottoNumbers(lotto) + "]\n")
        );
    }

    private String formatLottoNumbers(LottoDto lotto) {
        return lotto.numbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    public void printResult(Map<Rank, Integer> ranks) {
        System.out.println("당첨 통계 \n---------");
        Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.NO_PRIZE)
                .forEach(rank -> printRankResult(rank, ranks.getOrDefault(rank, 0)));
    }

    private void printRankResult(Rank rank, int count) {
        String bonusText = getBonusText(rank);
        System.out.println(getRankResultMessage(rank, count, bonusText));
    }

    private String getBonusText(Rank rank) {
        if (rank == Rank.SECOND) {
            return ", 보너스 볼 일치";
        }
        return "";
    }

    private String getRankResultMessage(Rank rank, int count, String bonusText) {
        return "%d개 일치%s (%d원) - %d개".formatted(rank.getMatchCount(), bonusText, rank.getWinningAmount(), count);
    }

    public void printWinningRatio(float ratio) {
        System.out.printf("총 수익률은 %.2f입니다.", ratio);
    }
}
