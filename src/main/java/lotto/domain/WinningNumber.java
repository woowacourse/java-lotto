package lotto.domain;

import lotto.domain.exception.LottoSizeException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WinningNumber {
    private final List<LottoNumber> winningNumbers;
    private final BonusBall bonusBall;

    public WinningNumber(final List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.size() != Lotto.LOTTO_SIZE) {
            throw new LottoSizeException("로또 당첨 번호는 6개여야 합니다.");
        }

        this.winningNumbers = makeWinningNumbers(winningNumbers);
        this.bonusBall = new BonusBall(bonusNumber);
    }

    private List<LottoNumber> makeWinningNumbers(final List<Integer> winningNumbers) {
        return winningNumbers.stream()
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toList());
    }

    public Prize prize(final Lotto lotto) {
        int matchCount = 0;
        boolean hasBonus = false;
        for (LottoNumber lottoNumber : lotto.getNumbers()) {
            matchCount += winningNumbers.contains(lottoNumber) ? 1 : 0;
            hasBonus |= bonusBall.isMatch(lottoNumber);
        }
        return Prize.valueOf(matchCount, hasBonus);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final WinningNumber that = (WinningNumber) o;
        return Objects.equals(winningNumbers, that.winningNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNumbers);
    }
}
