package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wallet {
    private final List<Lotto> lottos;

    public Wallet(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<MatchResult> getMatchResults(WinningInform winningInform) {
        List<MatchResult> matchResults = new ArrayList<>();
        for (Lotto lotto : lottos) {
            MatchResult matchResult = winningInform.match(lotto);
            matchResults.add(matchResult);
        }

        return matchResults;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
