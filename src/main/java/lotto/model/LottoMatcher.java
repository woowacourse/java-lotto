package lotto.model;

import java.util.List;

public class LottoMatcher {
    public static final String ERROR_DUPLICATION_BONUS_NUMBER = "보너스 볼 번호가 당첨 번호와 중복입니다.";
    private final WinningNumbers winningNumbers;
    private final BonusNumber bonusNumber;

    public LottoMatcher(List<Integer> winningNumbers, Integer bonusNumber) {
        validateDuplicateBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = new WinningNumbers(winningNumbers);
        this.bonusNumber = new BonusNumber(bonusNumber);
    }

    public LottoResult getWinningResult(Lottos lottos) {
        LottoResult lottoResult = new LottoResult();
        Integer defaultValue = 0;
        lottos.getLottos().forEach(lotto -> {
            lottoResult.getResult().put(match(lotto), lottoResult.getResult().getOrDefault(match(lotto), defaultValue) + 1);
        });
        return lottoResult;
    }

    public int matchWinningNumbers(Lotto lotto) {
        return lotto.match(winningNumbers);
    }

    public boolean matchBonus(Lotto lotto) {
        return lotto.matchBonusNumber(bonusNumber.getNumber());
    }

    public Rank match(Lotto lotto) {
        return Rank.parse(matchWinningNumbers(lotto), matchBonus(lotto));
    }

    private void validateDuplicateBonusNumber(List<Integer> winningNumbers, Integer bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_BONUS_NUMBER);
        }
    }
}
