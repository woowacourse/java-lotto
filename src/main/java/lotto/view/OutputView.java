package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.Rank;

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
    }

    private static String makeTicketsString(Set<LottoNumber> ticket) {
        String result = ticket.stream()
                .map(lottoNumber -> String.valueOf(lottoNumber.getNumber()))
                .collect(Collectors.joining(", "));

        return "[" + result + "]";
    }

    public static void printResult(Map<Rank, Integer> result, double profitRatio) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (Rank rank : result.keySet()) {
            int matched = rank.getMatched();
            int prize = rank.getPrize();
            Integer count = result.get(rank);

            if (rank == Rank.SECOND) {
                System.out.println(matched + "개 일치, 보너스 볼 일치 (" + prize + "원) - " + count + "개");
            }
            System.out.println(matched + "개 일치 (" + prize + "원) - " + count + "개");
        }
        System.out.println("총 수익률은 " + profitRatio + "입니다.");
    }

}
