package lotto.domain;

import java.util.Objects;

public class WinningInformation {
    private Lotto winningLotto;
    private LottoNumber bonus;

    public WinningInformation(Lotto winningLotto, LottoNumber bonus) {
        checkBonusOverlapWithWinningNumbers(winningLotto, bonus);
        this.winningLotto = winningLotto;
        this.bonus = bonus;
    }

    private void checkBonusOverlapWithWinningNumbers(Lotto winningLotto, LottoNumber bonus) {
        if (winningLotto.isContain(bonus)) {
            throw new IllegalArgumentException("보너스 번호는 당첨번호와 중복될 수 없습니다.");
        }
    }

    public Rank calculateRank(Lotto lotto) {
        int numberOfContain = countHowManyOverlapWithWinningLotto(lotto);
        boolean hasBonusLottoNumber = isBonusNumberIn(lotto);

        return Rank.getRank(numberOfContain, hasBonusLottoNumber);
    }

    private int countHowManyOverlapWithWinningLotto(Lotto lotto) {
        Objects.requireNonNull(lotto, "매개변수가 null 입니다.");
        return (int) lotto.getLottoNumbers().stream()
            .filter(lottoNumber -> winningLotto.isContain(lottoNumber))
            .count();
    }

    private boolean isBonusNumberIn(Lotto lotto) {
        Objects.requireNonNull(lotto, "매개변수가 null 입니다.");
        return lotto.getLottoNumbers().stream()
            .anyMatch(lottoNumber -> lottoNumber.equals(bonus));
    }
}
