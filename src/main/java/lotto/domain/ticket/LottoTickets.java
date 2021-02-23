package lotto.domain.ticket;

import lotto.domain.LottoResult;
import lotto.domain.Money;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public List<LottoTicket> list() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public int size() {
        return lottoTickets.size();
    }

    public LottoTickets concat(LottoTickets that) {
        this.lottoTickets.addAll(that.list());
        return new LottoTickets(lottoTickets);
    }

    public LottoResult calculateLottoResult(WinningLottoTicket winningLottoTicket, Money lottoPrice) {
        return lottoTickets.stream()
                .map(winningLottoTicket::compareNumbers)
                .collect(collectingAndThen(
                        groupingBy(Function.identity(), counting()),
                        lottoResultMap -> new LottoResult(lottoResultMap, lottoPrice.multiply(lottoTickets.size()))));
    }
}
