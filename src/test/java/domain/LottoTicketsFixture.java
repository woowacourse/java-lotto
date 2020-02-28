package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoTicketsFixture {
    private static AutoLottoTickets autoLottoTickets;

    static {
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(7)))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(8)))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(8),
                new LottoNumber(9)))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(8),
                new LottoNumber(9),
                new LottoNumber(10)))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(11),
                new LottoNumber(8),
                new LottoNumber(9),
                new LottoNumber(10)))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(12),
                new LottoNumber(11),
                new LottoNumber(8),
                new LottoNumber(9),
                new LottoNumber(10)))));
        autoLottoTickets = new AutoLottoTickets(lottoTicketList);
    }

    public static AutoLottoTickets getAutoLottoTickets() {
        return autoLottoTickets;
    }
}
