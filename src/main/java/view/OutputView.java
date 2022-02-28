package view;

import domain.LottoNumber;
import domain.LottoResults;
import domain.Rank;
import domain.Ticket;
import domain.Tickets;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";

    public static void printTicketCount(int ticketCount) {
        System.out.println(ticketCount + "개를 구매했습니다.");
    }

    public static void printTickets(Tickets tickets) {
        List<Ticket> purchasedTickets = tickets.getTickets();
        for (Ticket ticket : purchasedTickets) {
            System.out.print("[");
            Set<LottoNumber> lottoNumbers = ticket.getLottoNumbers();
            System.out.print(String.join(DELIMITER, getLottoNumbers(lottoNumbers)));
            System.out.println("]");
        }
    }

    private static List<String> getLottoNumbers(Set<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printResult(LottoResults results) {
        Map<Rank, Integer> LottoResults = results.getLottoResults();
        System.out.println("당첨 통계");
        System.out.println("---------");
        Rank.getRanks().stream()
                .filter(OutputView::isNotOther)
                .forEach(rank -> System.out.println(rank.getCount()
                        + "개 일치"
                        + getBonus(rank)
                        + "(" + rank.getAmount()
                        + "원) - "
                        + getRankCount(LottoResults, rank)
                        + "개"));
    }

    private static boolean isNotOther(Rank rank) {
        return rank != Rank.OTHER;
    }

    private static int getRankCount(Map<Rank, Integer> result, Rank rank) {
        if (result.containsKey(rank)) {
            return result.get(rank);
        }
        return 0;
    }

    private static String getBonus(Rank rank) {
        if (rank.getBonus()) {
            return ", 보너스 볼 일치";
        }
        return " ";
    }

    public static void printYield(double yield) {
        System.out.print("총 수익률은 " + yield + "입니다.");
        if (yield < 1) {
            System.out.print("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
        System.out.println();
    }

    public static void printTicketCount(int manualTicketCount, int autoTicketCount) {
        System.out.println("수동으로 " + manualTicketCount + "장, 자동으로 " + autoTicketCount + "개를 구매했습니다.");
    }
}
