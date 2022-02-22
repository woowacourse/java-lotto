package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGame {
    private final List<LottoTicket> lottoTickets;

    public LottoGame(int purchaseMoney){
        lottoTickets = new ArrayList<>();
        for (int i = 0; i < purchaseMoney/1000; i++) {
            lottoTickets.add(new LottoTicket(new LottoNumberGenerateStrategy()));
        }
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
