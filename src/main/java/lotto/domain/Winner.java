package lotto.domain;

import java.util.List;
import java.util.Objects;

/**
 * @author heebg
 * @version 1.0 2019-06-02
 */
public class Winner {
    private CustomLotto customLotto;
    private Lotto winLotto;
    private LottoNumber winBonus;

    public void setCustomLotto(CustomLotto customLotto) {
        this.customLotto = customLotto;
    }

    public void customWinLotto(List<Integer> noFormedLottoNumber) {
        this.winLotto = Lotto.createLotto(customLotto.custom(noFormedLottoNumber));
    }

    public int matchLottoCount(Lotto lotto) {
        return winLotto.matchCount(lotto);
    }

    public void customWinBonus(int winBonus) {
        this.winBonus = new LottoNumber(winBonus);
        checkBonusCondition();
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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Winner winner = (Winner) o;
        return Objects.equals(customLotto, winner.customLotto) &&
                Objects.equals(winLotto, winner.winLotto) &&
                Objects.equals(winBonus, winner.winBonus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winLotto, winBonus);
    }
}
