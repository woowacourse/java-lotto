package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketFactory {

    private final static int LOTTO_TICKET_PRICE = 1000;

    public static List<LottoTicket> generateAuto(final Wallet wallet) {
        final List<LottoTicket> lottoTickets = new ArrayList<>();
        while (wallet.pay(LOTTO_TICKET_PRICE)) {
            lottoTickets.add(LottoTicket.generateRandom());
        }
        return lottoTickets;
    }
}
