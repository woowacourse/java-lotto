package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTicket {

    private final List<Lotto> lottos;

    public LottoTicket(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<MatchResultDto> deriveMatchResults(final WinningNumber winningNumber,
                                                   final BonusNumber bonusNumber) {
        List<MatchResultDto> results = new ArrayList<>();
        for (Lotto lotto : lottos) {
            results.add(lotto.deriveMatchResult(winningNumber, bonusNumber));
        }
        return results;

    }
}
