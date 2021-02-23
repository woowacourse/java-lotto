package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.result.LottoGroup;

public class TicketsView {

    private TicketsView() {
    }

    public static void printTickets(List<LottoTicket> lottoTickets) {
        printLottoTicketCount(lottoTickets.size());

        for (LottoTicket lottoTicket : lottoTickets) {
            printLottoTicket(lottoTicket);
        }

        System.out.println();
    }

    private static void printLottoTicketCount(int lottoTicketCount) {
        System.out.printf("%d개를 구매했습니다.\n", lottoTicketCount);
    }

    private static void printLottoTicket(LottoTicket lottoTicket) {
        String numbers = lottoTicket.toUnmodifiableList().stream()
                .map(lottoNumber -> lottoNumber.toInteger().toString())
                .collect(Collectors.joining(", ", "[", "]"));

        System.out.println(numbers);
    }


    public static void printTickets(LottoGroup lottoGroup) {
        printLottoTicketCount(lottoGroup.getAutoTicketsSize(), lottoGroup.getManualTicketsSize());

        for (LottoTicket lottoTicket : lottoGroup.getTotalTickets()) {
            printLottoTicket(lottoTicket);
        }

        System.out.println();
    }

    private static void printLottoTicketCount(int automaticLottoCount, int manualLottoCount) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", automaticLottoCount, manualLottoCount);
    }
}
