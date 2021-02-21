package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {

    private final static int LOTTO_TICKET_PRICE = 1000;
    private final static int MIN_LOTTO_NUMBER = 1;
    private final static int MAX_LOTTO_NUMBER = 45;

    private final Price price;

    private LottoMachine(final Price price) {
        this.price = price;
    }

    public static LottoMachine valueOf(final Price price) {
        return new LottoMachine(price);
    }

    public List<LottoTicket> generateLottoTickets() {
        final List<LottoTicket> lottoTickets = new ArrayList<>();
        final int lottoTicketQuantity = getLottoTicketQuantity();
        for (int i = 0; i < lottoTicketQuantity; i++) {
            lottoTickets.add(LottoTicket.valueOf(
                    RandomLottoNumberGenerator.generate(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)));
        }
        return lottoTickets;
    }

    private int getLottoTicketQuantity() {
        return price.getValue() / LOTTO_TICKET_PRICE;
    }
}
