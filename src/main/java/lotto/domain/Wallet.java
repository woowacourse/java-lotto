package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private final List<Lotto> lottos;

    public Wallet(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<MatchResult> getMatchResults(Lotto matchLotto, int bonus) {
        List<MatchResult> matchResults = new ArrayList<>();

        for (Lotto lotto : lottos) {
            MatchResult dto = lotto.countMatchingNumbers(matchLotto, bonus);
            matchResults.add(dto);
        }

        return matchResults;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Lotto lotto : lottos) {
            sb.append(lotto.toString());
            sb.append("\n");
        }

        return sb.toString();
    }
}
