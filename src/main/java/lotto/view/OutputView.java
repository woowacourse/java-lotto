package lotto.view;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lotto.domain.LottoNumber;
import lotto.domain.Rank;
import lotto.domain.Ticket;
import lotto.domain.Tickets;

public class OutputView {

    private static final String PURCHASE_MESSAGE = "개를 구매했습니다.";
    private static final String LOSS_MESSAGE = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    private static final String DELIMITER = ", ";

    private static void printTicketCount(int ticketCount) {
        System.out.println(ticketCount + PURCHASE_MESSAGE);
    }

    public static void printTickets(int ticketCount, Tickets tickets) {
        printTicketCount(ticketCount);
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

    public static void printResult(Map<Rank, Integer> result, double yield) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (Rank rank : Rank.getRanks()) {
            System.out.println(rank.getCount()
                + "개 일치"
                + getBonus(rank)
                + "(" + rank.getAmount()
                + "원) - "
                + getCount(result, rank)
                + "개");
        }
        printYield(yield);
    }

    private static int getCount(Map<Rank, Integer> result, Rank rank) {
        if (result.containsKey(rank)) {
            return result.get(rank);
        }
        return 0;
    }

    private static String getBonus(Rank rank) {
        if (rank.isBonus()) {
            return ", 보너스 볼 일치";
        }
        return " ";
    }

    private static void printYield(double yield) {
        System.out.print("총 수익률은 " + yield + "입니다.");
        if (yield < 1) {
            System.out.print(LOSS_MESSAGE);
        }
        System.out.println();
    }
}
