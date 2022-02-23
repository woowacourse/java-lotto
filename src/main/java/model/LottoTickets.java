package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(int purchaseMoney, GenerateStrategy generateStrategy) {
        List<LottoTicket> result = new ArrayList<>();
        for (int i = 0; i < purchaseMoney / 1000; i++) {
            result.add(new LottoTicket(generateStrategy));
        }

        lottoTickets = result;
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public int size() {
        return lottoTickets.size();
    }
}
