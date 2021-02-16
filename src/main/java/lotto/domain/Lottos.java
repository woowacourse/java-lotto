package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Results> getResults(List<Integer> winningNumbers, int bonusNumber) {
        List<Results> results = new ArrayList<>();
        for (Lotto lotto : lottos) {
            int matches = lotto.countMatchingNumbers(winningNumbers);
            boolean bonusMatch = lotto.isBonusMatch(bonusNumber);

            if (matches == 6) {
                results.add(Results.FIRST);
                continue;
            }
            if (matches == 5 && bonusMatch) {
                results.add(Results.SECOND);
                continue;
            }
            if (matches == 5 && !bonusMatch) {
                results.add(Results.THIRD);
                continue;
            }
            if (matches == 4) {
                results.add(Results.FOURTH);
                continue;
            }
            if (matches == 3) {
                results.add(Results.FIFTH);
                continue;
            }
            if (matches < 3) {
                results.add(Results.NONE);
                continue;
            }
        }
        return results;
    }
}
