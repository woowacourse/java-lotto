package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Lotto {
    private static final int BONUS_REQUIRED_RANK_NUMBER = 5;
    private static final int LOTTO_SIZE = 6;

    private final List<Integer> lotto;

    public Lotto(long currentTime) {
        this.lotto = generateLotto(currentTime);
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

    public List<Integer> getLotto() {
        return Collections.unmodifiableList(lotto);
    }

    private List<Integer> generateLotto(long currentTime) {
        Set<Integer> lotto = new HashSet<>();
        Random random = new Random();
        random.setSeed(currentTime);

        while (lotto.size() < LOTTO_SIZE) {
            int number = random.nextInt(45) + 1;
            lotto.add(number);
        }
        return new ArrayList<>(lotto);
    }
}
