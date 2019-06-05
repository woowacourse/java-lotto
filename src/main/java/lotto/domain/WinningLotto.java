package lotto.domain;

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
}
