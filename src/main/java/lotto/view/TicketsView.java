package lotto.view;

import java.util.stream.Collectors;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.result.UsersLottoTickets;

public class TicketsView {

    private TicketsView() {
    }

    public static void printTickets(UsersLottoTickets usersLottoTickets) {
        printLottoTicketCount(usersLottoTickets.getAutoTicketsSize(),
                usersLottoTickets.getManualTicketsSize());

        for (LottoTicket lottoTicket : usersLottoTickets.getTotalTickets()) {
            printLottoTicket(lottoTicket);
        }
    }

    private static void printLottoTicketCount(int automaticLottoCount, int manualLottoCount) {
        printNotice(String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n",
                automaticLottoCount, manualLottoCount));
    }

    private static void printLottoTicket(LottoTicket lottoTicket) {
        String numbers = lottoTicket.toUnmodifiableList().stream()
                .map(lottoNumber -> lottoNumber.toInteger().toString())
                .collect(Collectors.joining(", ", "[", "]"));

        System.out.println(numbers);
    }

    private static void printNotice(String notice) {
        System.out.println("\n" + notice);
    }
}
