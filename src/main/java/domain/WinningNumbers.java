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

    public static double calculateYield(Map<Rank, Integer> result, int purchaseAmount) {
        long sum = result.entrySet().stream()
                .mapToLong(set -> set.getKey().getPrizeMoney() * set.getValue())
                .sum();
        return (double) sum / purchaseAmount;
    }

    private static void validateBonusNumberNotInWinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.hasNumber(bonusNumber)) {
            throw new IllegalArgumentException(ErrorCode.WINNING_LOTTO_CONTAINS_BONUS_NUMBER.getMessage());
        }
    }

    public Rank getRank(Lotto lotto) {
        return Rank.findByMatchCountAndBonus(lotto.calculateMatchCount(winningLotto), lotto.hasNumber(bonusNumber));
    }
}
