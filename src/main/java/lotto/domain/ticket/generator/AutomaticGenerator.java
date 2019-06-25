package lotto.domain.ticket.generator;

import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.LottoTicket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AutomaticGenerator implements Generator {
    private static List<LottoNumber> randomLottoNumberPull;

    static {
        randomLottoNumberPull = new ArrayList<>();
        randomLottoNumberPull.addAll(LottoNumber.getLottoNumberPool());
    }

    private int quantity;

    public AutomaticGenerator(int quantity) {
        this.quantity = quantity;
    }

    public List<LottoTicket> generateTickets() {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            lottoTickets.add(generateTicket());
        }
        return lottoTickets;
    }

    private LottoTicket generateTicket() {
        return new LottoTicket(generateNumber());
    }

    private List<LottoNumber> generateNumber() {
        final int INIT_INDEX = 0;
        Collections.shuffle(randomLottoNumberPull, new Random());
        return randomLottoNumberPull.subList(INIT_INDEX, LottoTicket.getMaxLottoTicketNumber());
    }
}
