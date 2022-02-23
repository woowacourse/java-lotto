package view;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import domain.LottoNumber;
import domain.Ticket;
import domain.Tickets;

public class OutputView {

    public static final String PURCHASE_MESSAGE = "개를 구매했습니다.";

    public static void printTicketCount(int ticketCount) {
        System.out.println(ticketCount + PURCHASE_MESSAGE);
    }

    public static void printTickets(Tickets tickets) {
        List<Ticket> purchasedTickets = tickets.getTickets();
        for (Ticket ticket : purchasedTickets) {
            System.out.print("[");
            Set<LottoNumber> lottoNumbers = ticket.getLottoNumbers();
            System.out.print(String.join(", ", getLottoNumbers(lottoNumbers)));
            System.out.println("]");
        }
    }

    private static List<String> getLottoNumbers(Set<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
            .map(LottoNumber::getNumber)
            .map(String::valueOf)
            .collect(Collectors.toList());
    }
}
