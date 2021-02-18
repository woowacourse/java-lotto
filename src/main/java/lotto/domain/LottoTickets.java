package lotto.domain;

import lotto.domain.ticketfactory.RandomNumberTicketFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private static final int TICKET_SIZE = 8;
    private static final int INITIAL_HIT_COUNT = 0;

    private final List<LottoTicket> lottoTickets;

    public LottoTickets() {
        this.lottoTickets = new ArrayList<>();
    }

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public List<Integer> checkHitCount(LottoTicket winningTicket, LottoNumber bonusBall) {
        List<Integer> winningCount = new ArrayList<>(Collections.nCopies(TICKET_SIZE, INITIAL_HIT_COUNT));
        for (LottoTicket lottoTicket : lottoTickets) {
            int hitCount = lottoTicket.compareNumbers(winningTicket, bonusBall);
            winningCount.set(hitCount, winningCount.get(hitCount) + 1);
        }
        return winningCount;
    }

    public List<LottoTicket> toList() {
        return new ArrayList<>(lottoTickets);
    }

    public void makeTicketByCount(int counts) {
        for (int i = 0; i < counts; i++) {
            lottoTickets.add(RandomNumberTicketFactory.makeTicket());
        }
    }
}
