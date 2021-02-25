package lotto.domain.lotto;

import lotto.domain.result.LottoResult;
import lotto.domain.result.WinningLotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = new ArrayList<>(lottoTickets);
    }

    public List<LottoTicket> lottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    public static LottoTickets auto(int count) {
        return Stream.generate(LottoTicket::auto)
                .limit(count)
                .collect(collectingAndThen(toList(), LottoTickets::new));
    }

    public LottoResult checkPrizes(WinningLotto winningLotto) {
        return lottoTickets.stream()
                .map(t -> t.matchPrize(winningLotto.getWinningTicket(), winningLotto.getBonusNumber()))
                .collect(collectingAndThen(toList(), LottoResult::new));
    }

    public void combine(LottoTickets anotherLottoTickets) {
        this.lottoTickets.addAll(anotherLottoTickets.lottoTickets);
    }
}
