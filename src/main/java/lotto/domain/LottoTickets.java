package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {

    public static final int NUMBER_OF_WINNING_TYPE = 8;

    private final List<LottoTicket> lottoTickets;

    public LottoTickets() {
        this.lottoTickets = new ArrayList<>();
    }

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = new ArrayList<>(lottoTickets);
    }

    public void addTicket(LottoTicket lottoTicket) {
        lottoTickets.add(lottoTicket);
    }

    public List<Integer> checkHitCount(WinningLotto winningLotto) {
        List<Integer> winningCount = new ArrayList<>(
            Collections.nCopies(NUMBER_OF_WINNING_TYPE, 0));
        for (LottoTicket lottoTicket : lottoTickets) {
            int hitCount = lottoTicket.compareNumbers(winningLotto);
            winningCount.set(hitCount, winningCount.get(hitCount) + 1);
        }
        return winningCount;
    }

    public List<LottoTicket> toList() {
        return new ArrayList<>(lottoTickets);
    }
}
