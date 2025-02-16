package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Lotto {
    private static final int BONUS_REQUIRED_RANK_NUMBER = 5;

    private final Set<Integer> lotto;

    public Lotto(RandomNumberGenerator randomNumberGenerator) {
        this.lotto = randomNumberGenerator.generateNumbers();
    }

    public Rank getRank(WinningLotto winningLotto) {
        int duplicateNumber = getDuplicateNumber(winningLotto);
        if (duplicateNumber == BONUS_REQUIRED_RANK_NUMBER && isBonusMatch(winningLotto.getBonus())) {
            return Rank.SECOND;
        }

        return Arrays.stream(Rank.values())
                .filter(rank -> rank.getMatchNumber() == duplicateNumber)
                .findFirst()
                .orElse(Rank.FAIL);
    }

    private int getDuplicateNumber(WinningLotto winningLotto) {
        Set<Integer> union = new HashSet<>();
        union.addAll(winningLotto.getWinningNumbers());
        union.addAll(lotto);
        return 12 - union.size();
    }

    private boolean isBonusMatch(int bonus) {
        return lotto.contains(bonus);
    }

    public Set<Integer> getLotto() {
        return Collections.unmodifiableSet(lotto);
    }
}
