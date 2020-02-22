package lotto.domain.result;

import lotto.domain.result.win.rank.Rank;

import java.util.List;
import java.util.stream.Collectors;

public class MatchResultBundle {
    private final List<MatchResult> matchResults;

    public MatchResultBundle(List<MatchResult> matchResults) {
        this.matchResults = matchResults;
    }

    public LottoResultBundle getPrizeBundle() {
        return new LottoResultBundle(getPrizeGroup());
    }

    private List<Rank> getPrizeGroup() {
        return this.matchResults.stream()
                .map(Rank::findPrizeByLottoResult)
                .collect(Collectors.toList());
    }
}
