package domain;

import domain.ticket.LottoTicket;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final LottoMoney lottoMoney;

    private LottoMachine(final LottoMoney lottoMoney) {
        this.lottoMoney = lottoMoney;
    }

    public static LottoMachine valueOf(final LottoMoney lottoMoney) {
        return new LottoMachine(lottoMoney);
    }

    public List<LottoTicket> generateLottoTickets() {
        final List<LottoTicket> lottoTickets = new ArrayList<>();
        final int lottoTicketQuantity = lottoMoney.toTicketQuantity();

        for (int i = 0; i < lottoTicketQuantity; i++) {
            lottoTickets.add(LottoTicket.valueOf(
                    RandomLottoNumberGenerator.generate(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)));
        }
        return lottoTickets;
    }
}
