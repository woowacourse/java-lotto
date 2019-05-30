package lotto.domain;

import lotto.domain.exception.LottoSizeException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WinningNumber {
    private final WinningLotto winningLotto;
    private final BonusBall bonusBall;

    public WinningNumber(final List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.size() != Lotto.LOTTO_SIZE) {
            throw new LottoSizeException("로또 당첨 번호는 6개여야 합니다.");
        }

        this.winningLotto = new WinningLotto(makeWinningNumbers(winningNumbers));
        this.bonusBall = new BonusBall(bonusNumber);
    }

    private Lotto makeWinningNumbers(final List<Integer> winningNumbers) {
        return new Lotto(winningNumbers.stream()
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toList()));
    }

    public Prize prize(final Lotto lotto) {
        int matchCount = winningLotto.countOfMatchLottoNumber(lotto);
        boolean hasBonus = lotto.hasBonusBall(bonusBall);
        return Prize.valueOf(matchCount, hasBonus);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final WinningNumber that = (WinningNumber) o;
        return Objects.equals(winningLotto, that.winningLotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLotto);
    }
}
