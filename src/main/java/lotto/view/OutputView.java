package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.RankBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printTicketCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printTickets(List<Lotto> tickets) {
        for (Lotto ticket : tickets) {
            System.out.println(makeTicketString(ticket));
        }
        System.out.println();
    }

    private static String makeTicketString(Lotto ticket) {
        String result = ticket.getIntValues().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        return "[" + result + "]";
    }

    public static void printResult(RankBoard rankBoard, double profitRatio) {
        printResultTitle();
        printRankCounter(rankBoard);
        printProfitRatio(profitRatio);
    }

    private static void printResultTitle() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private static void printRankCounter(RankBoard rankBoard) {
        ArrayList<Rank> ranks = new ArrayList<>(Arrays.asList(Rank.values()));
        ranks.sort(Comparator.comparing(Rank::getPrize));
        for (Rank rank : ranks) {
            printRank(rank, rankBoard);
        }
    }

    private static void printRank(Rank rank, RankBoard rankBoard) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(rank.getMatched()).append("개 일치");
        if (rank == Rank.SECOND) {
            stringBuilder.append(", 보너스 볼 일치");
        }
        stringBuilder.append(" (").append(rank.getPrize()).append("원) - ").append(rankBoard.getCount(rank)).append("개");

        System.out.println(stringBuilder);
    }

    private static void printProfitRatio(double profitRatio) {
        System.out.println("총 수익률은 " + profitRatio + "입니다.");
    }
}
