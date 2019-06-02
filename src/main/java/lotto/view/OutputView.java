package lotto.view;

import lotto.domain.LottoTicket;

import java.util.List;

public class OutputView {
    public static void printLottoTickets(final int amountOfCustoms, final int amountOfLottoTickets, final List<LottoTicket> lottoTickets) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", amountOfCustoms, amountOfLottoTickets - amountOfCustoms);

        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket);
        }
    }

    public static void printResult() {
        System.out.println("당첨 통계\n" + "---------");
    }
}
