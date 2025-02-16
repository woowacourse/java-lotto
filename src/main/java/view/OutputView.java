package view;

import domain.Rank;
import dto.response.LottosResponse;
import dto.response.WinningResultResponse;
import java.util.Arrays;
import java.util.Map;

public class OutputView {
    private static final String WINNING_COUNT_FORMAT = "%d개 일치 (%d원)- %d개" + System.lineSeparator();
    private static final String WINNING_COUNT_WITH_BONUS_FORMAT =
            "%d개 일치, 보너스 볼 일치 (%d원)- %d개" + System.lineSeparator();


    public void printLottoQuantity(int quantity) {
        System.out.println(quantity + "개를 구매했습니다.");
    }

    public void printLottos(LottosResponse response) {
        response.lottos().stream()
                .map(LottosResponse.LottoNumbers::value)
                .forEach(System.out::println);

        System.out.println();
    }

    public void printWinningResult(WinningResultResponse response) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        Arrays.stream(Rank.values())
                .forEach(rank ->
                        System.out.print(getRankCountMessage(rank, response.rankCount())));

        System.out.println(String.format("총 수익률은 %.2f입니다.", response.yield()));
    }

    private String getRankCountMessage(Rank rank, Map<Rank, Integer> rankCount) {
        if (rank == Rank.NONE) {
            return "";
        }
        if (rank == Rank.FIVE_WITH_BONUS) {
            return String.format(WINNING_COUNT_WITH_BONUS_FORMAT,
                    rank.getMatchCount(), rank.getPrizeMoney(), rankCount.getOrDefault(rank, 0));
        }
        return String.format(WINNING_COUNT_FORMAT,
                rank.getMatchCount(), rank.getPrizeMoney(), rankCount.getOrDefault(rank, 0));
    }
}
