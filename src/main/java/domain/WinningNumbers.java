package domain;

import java.util.Map;

public class WinningNumbers {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    private WinningNumbers(Lotto winningLotto, LottoNumber bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningNumbers createByWinningLottoAndBonusNumber(Lotto winningLotto, int bonusNumber) {
        LottoNumber bonusLottoNumber = LottoNumber.of(bonusNumber);
        validateBonusNumberNotInWinningLotto(winningLotto, bonusLottoNumber);

        return new WinningNumbers(winningLotto, bonusLottoNumber);
    }

    private static void validateBonusNumberNotInWinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.hasNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 숫자는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Rank getRank(Lotto lotto) {
        return Rank.findByMatchCountAndBonus(lotto.calculateMatchCount(winningLotto), lotto.hasNumber(bonusNumber));
    }

    public double calculateYield(Map<Rank, Integer> result, int purchaseAmount) {
        long sum = result.entrySet().stream()
                .mapToLong(set -> set.getKey().getPrizeMoney() * set.getValue())
                .sum();
        return (double) sum / purchaseAmount;
    }
}
