package lotto.domain.ticket;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import lotto.domain.LottoResult;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public LottoTickets(LottoTickets lottoTickets, LottoTickets joinLottoTickets) {
        this.lottoTickets = lottoTickets.getLottoTickets();
        this.lottoTickets.addAll(joinLottoTickets.getLottoTickets());
    }

    public LottoResult calculateLottoResult(WinningLottoTicket winningTicket) {
        return lottoTickets.stream()
            .map(winningTicket::calculatePrize)
            .collect(collectingAndThen(
                groupingBy(Function.identity(), counting()),
                LottoResult::new));
    }

    public List<LottoTicket> getLottoTickets() {
        return new ArrayList<>(lottoTickets);
    }

}
