package lotto.domain;

import java.util.List;

public class LottoTicket {

    private final List<Lotto> lottos;

    public LottoTicket(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<MatchResultDto> deriveMatchResults(final WinningNumber winningNumber,
                                                   final BonusNumber bonusNumber) {
        return lottos.stream()
                .map(lotto -> lotto.deriveMatchResult(winningNumber, bonusNumber))
                .toList();
    }
}
