package lotto.model;

import java.util.List;

public class LottoGame {
    private static final String ERROR_DUPLICATION_BONUS_NUMBER = "보너스 볼 번호가 당첨 번호와 중복입니다.";

    private final WinningNumbers winningNumbers;
    private final LottoNumber bonusNumber;

    public LottoGame(List<Integer> winningNumbers, Integer bonusNumber) {
        validateDuplicateBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = new WinningNumbers(winningNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public static Lottos buyLottos(Money money) {
        return Lottos.generate(money.getLottoSize());
    }

    private void validateDuplicateBonusNumber(List<Integer> winningNumbers, Integer bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_BONUS_NUMBER);
        }
    }

    public LottoResult generateLottoResult(Lottos lottos) {
        return LottoResult.create(lottos, winningNumbers, bonusNumber);
    }

    public Yield calculateYield(Money money, LottoResult lottoResult) {
        return money.calculateYield(lottoResult.getTotalWinningMoney());
    }
}
