package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto {
    private static final String ERROR_DUPLICATE = "당첨 번호와 중복되었습니다.";

    private final Lotto winnerLotto;
    private final Number bonus;

    public WinningLotto(Lotto winnerLotto, Number bonus) {
        this.winnerLotto = winnerLotto;
        this.bonus = bonus;

        checkDuplicate();
    }

    public boolean matchBonus(Lotto lotto) {
        return lotto.isContains(bonus);
    }

    public int match(Lotto lotto) {
        int count = 0;

        for (int i = 0; i < lotto.getSize(); i++) {
            count = addCount(lotto, count, i);
        }

        return count;
    }

    public int getBonus() {
        return Integer.parseInt(bonus.toString());
    }

    public List<Rank> makeRankResultList(UserLotto userLotto) {
        List<Rank> rankResults = new ArrayList<>();

        for (int i = 0; i < userLotto.getSize(); i++) {
            rankResults.add(Rank.valueOf(this.match(userLotto.getIndexByLotto(i))
                    , this.matchBonus(userLotto.getIndexByLotto(i))));
        }

        return rankResults;
    }

    private int addCount(Lotto lotto, int count, int i) {
        if (winnerLotto.isContains(lotto.getLottoByIndex(i))) {
            count++;
        }
        return count;
    }

    private void checkDuplicate() {
        if (winnerLotto.isContains(bonus)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE);
        }
    }

    @Override
    public String toString() {
        return winnerLotto.getLotto().stream()
                .map(Number::toString)
                .collect(Collectors.joining(","));
    }
}
