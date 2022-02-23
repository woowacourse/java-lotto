package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.Rank;
import lotto.domain.Result;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {

    public static void printTicketCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printTickets(List<Set<LottoNumber>> tickets) {
        for (Set<LottoNumber> ticket : tickets) {
            System.out.println(makeTicketsString(ticket));
        }
        System.out.println();
    }

    private static String makeTicketsString(Set<LottoNumber> ticket) {
        String result = ticket.stream()
                .map(lottoNumber -> String.valueOf(lottoNumber.getNumber()))
                .collect(Collectors.joining(", "));

        return "[" + result + "]";
    }

    public static void printResult(Result result) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        System.out.println(makeResultRankCounterString(result));
        System.out.println("총 수익률은 " + result.getProfitRatio() + "입니다.");
    }

    private static String makeResultRankCounterString(Result result) {
        Map<Rank, Integer> rankCounter = result.getRankCounter();
        StringBuilder stringBuilder = new StringBuilder();

        for (Rank rank : rankCounter.keySet()) {
            addResultString(rank, rankCounter, stringBuilder);
        }
        return stringBuilder.toString();
    }

    private static void addResultString(Rank rank, Map<Rank, Integer> rankCounter, StringBuilder stringBuilder) {
        stringBuilder.append(rank.getMatched() + "개 일치");
        if (rank == Rank.SECOND) {
            stringBuilder.append(", 보너스 볼 일치");
        }
        stringBuilder.append(" (" + rank.getPrize() + "원) - " + rankCounter.get(rank) + "개\n");
    }
}
