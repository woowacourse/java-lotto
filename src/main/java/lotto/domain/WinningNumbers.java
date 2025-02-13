package lotto.domain;

import static lotto.constant.ErrorMessage.WINNING_NUMBERS_CONTAIN_BONUS_NUMBER;

public class WinningNumbers {
    private Lotto winningLotto;
    private LottoNumber bonusNumber;

    public WinningNumbers(Lotto lotto, int bonusNumber) {
        LottoNumber generatedBonusNumber = new LottoNumber(bonusNumber);
        if (lotto.containsNumber(generatedBonusNumber)) {
            throw new IllegalArgumentException(WINNING_NUMBERS_CONTAIN_BONUS_NUMBER.getMessage());
        }
        this.winningLotto = lotto;
        this.bonusNumber = generatedBonusNumber;
    }

    public Rank getRank(Lotto lotto) {
        int matchCount = lotto.findMatchCount(winningLotto);
        boolean matchBonus = lotto.containsNumber(bonusNumber);
        return Rank.of(matchCount, matchBonus);
    }
}
