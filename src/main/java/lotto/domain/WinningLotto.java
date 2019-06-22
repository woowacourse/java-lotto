package lotto.domain;

import lotto.exception.BonusBallValidException;
import lotto.utils.Converter;

import java.util.Objects;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonus;

    public WinningLotto(Lotto winningLotto, LottoNumber bonus) {
        checkWinningLotto(winningLotto, bonus);
        this.winningLotto = winningLotto;
        this.bonus = bonus;
    }

    private void checkWinningLotto(Lotto winningLotto, LottoNumber bonusBall) {
        if (winningLotto.isContain(bonusBall)) {
            throw new BonusBallValidException("보너스볼은 당첨 번호에 없어야 합니다.");
        }
    }

    public static WinningLotto generateWinningLotto(String winningInput, int bonusInput) {
        Lotto lotto = new Lotto(Converter.convertNumbers(winningInput));
        return new WinningLotto(lotto, LottoNumber.valueOf(bonusInput));
    }

    Rank matchLotto(Lotto lotto) {
        int countOfMatch = winningLotto.matchNumber(lotto);
        boolean matchBonus = lotto.isContain(bonus);
        return Rank.valueOf(countOfMatch, matchBonus);
    }

    public Lotto getWinningNumbers() {
        return winningLotto;
    }

    public LottoNumber getBonus() {
        return bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(winningLotto, that.winningLotto) &&
                Objects.equals(bonus, that.bonus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLotto, bonus);
    }

}
