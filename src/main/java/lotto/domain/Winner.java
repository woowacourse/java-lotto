package lotto.domain;

import java.util.Objects;

/**
 * @author heebg
 * @version 1.0 2019-06-02
 */
public class Winner {
    private final Lotto winLotto;
    private final LottoNumber winBonus;

    public Winner(Lotto winLotto, LottoNumber winBonus) {
        this.winLotto = winLotto;
        this.winBonus = winBonus;
        checkBonusCondition();
    }

    public int matchLottoCount(Lotto lotto) {
        return winLotto.matchCount(lotto);
    }

    private void checkBonusCondition() {
        if (winLotto.contains(winBonus)) {
            throw new IllegalArgumentException("보너스 번호는 우승번호들과 겹치지 말아야 합니다.");
        }
    }

    public boolean matchBonus(Lotto lotto) {
        return lotto.contains(winBonus);
    }

    public Rank generateRank(Lotto lotto) {
        return Rank.valueOf(matchLottoCount(lotto), matchBonus(lotto));
    }

    public LottoNumber winLotto(int index) {
        return winLotto.get(index);
    }

    public LottoNumber getBonus() {
        return winBonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Winner winner = (Winner) o;
        return Objects.equals(winLotto, winner.winLotto) &&
                Objects.equals(winBonus, winner.winBonus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winLotto, winBonus);
    }
}
