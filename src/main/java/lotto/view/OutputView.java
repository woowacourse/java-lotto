package lotto.view;

import lotto.domain.LottoNumbers;
import lotto.domain.Rank;
import lotto.domain.Result;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printTicketCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printTickets(List<LottoNumbers> tickets) {
        for (LottoNumbers ticket : tickets) {
            System.out.println(makeTicketsString(ticket));
        }
        System.out.println();
    }

    private static String makeTicketsString(LottoNumbers ticket) {
        String result = ticket.intValues().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        return "[" + result + "]";
    }

    public static void printResult(Result result) {
        printResultTitle();
        printRankCounter(result);
        printProfitRatio(result);
    }

    private static void printResultTitle() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private static void printRankCounter(Result result) {
        Map<Rank, Integer> rankCounter = result.getRankCounter();

        ArrayList<Rank> ranks = new ArrayList<>(rankCounter.keySet());
        ranks.sort(Comparator.comparing(Rank::getMatched));

        for (Rank rank : ranks) {
            printEachRank(rank, rankCounter);
        }
    }

    private static void printEachRank(Rank rank, Map<Rank, Integer> rankCounter) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(rank.getMatched() + "개 일치");
        if (rank == Rank.SECOND) {
            stringBuilder.append(", 보너스 볼 일치");
        }
        stringBuilder.append(" (" + rank.getPrize() + "원) - " + rankCounter.get(rank) + "개");

        System.out.println(stringBuilder);
    }

    private static void printProfitRatio(Result result) {
        System.out.println("총 수익률은 " + result.getProfitRatio() + "입니다.");
    }
}
