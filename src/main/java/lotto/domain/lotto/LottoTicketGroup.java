package lotto.domain.lotto;

import lotto.domain.lottoresult.LottoRank;
import lotto.domain.lottoresult.LottoResult;
import lotto.domain.lottoresult.WinningLotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketGroup implements Iterable<LottoTicket> {
    private final List<LottoTicket> lottoTickets;

    public LottoTicketGroup(List<LottoTicket> lottoTickets) {
        this.lottoTickets = Collections.unmodifiableList(lottoTickets);
    }

    public int size() {
        return lottoTickets.size();
    }

    public int price() {
        return size() * LottoTicket.PRICE;
    }

    public LottoTicketGroup combine(LottoTicketGroup lottos) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        lottoTickets.addAll(this.lottoTickets);
        lottoTickets.addAll(lottos.lottoTickets);

        return new LottoTicketGroup(lottoTickets);
    }

    public LottoResult match(WinningLotto winningLotto) {
        return new LottoResult(lottoTickets.stream()
                .map(winningLotto::match)
                .collect(Collectors.toList())
        );
    }

    @Override
    public Iterator<LottoTicket> iterator() {
        return lottoTickets.iterator();
    }
}
