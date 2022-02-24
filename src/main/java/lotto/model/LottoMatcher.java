package lotto.model;

import java.util.List;

public class LottoMatcher {
    public static final String ERROR_DUPLICATION_BONUS_NUMBER = "보너스 볼 번호가 당첨 번호와 중복입니다.";
    private final WinningNumbers winningNumbers;
    private final LottoNumber bonusNumber;

    public LottoMatcher(List<Integer> winningNumbers, Integer bonusNumber) {
        validateDuplicateBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = new WinningNumbers(winningNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public LottoResult getWinningResult(Lottos lottos) {
        LottoResult lottoResult = new LottoResult();

        return lottoResult.generate(lottos, winningNumbers, bonusNumber);
    }

    private void validateDuplicateBonusNumber(List<Integer> winningNumbers, Integer bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_BONUS_NUMBER);
        }
    }
}
