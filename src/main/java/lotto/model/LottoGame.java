package lotto.model;

import java.util.List;

public class LottoGame {
    public static final String ERROR_DUPLICATION_BONUS_NUMBER = "보너스 볼 번호가 당첨 번호와 중복입니다.";

    private final WinningNumbers winningNumbers;
    private final LottoNumber bonusNumber;
    private final LottoResult lottoResult;

    public LottoGame(List<Integer> winningNumbers, Integer bonusNumber) {
        validateDuplicateBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = new WinningNumbers(winningNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
        this.lottoResult = new LottoResult();
    }

    public float calculateYield(Money money) {
        return money.calculatePercentage(getTotalWinningMoney(lottoResult));
    }

    private Long getTotalWinningMoney(LottoResult lottoResult) {
        return lottoResult.getResult().entrySet().stream()
                .map(entry -> entry.getKey().getMoney() * entry.getValue())
                .mapToLong(i -> i)
                .sum();
    }

    public static Lottos buyLottos(Money money) {
        return Lottos.generate(money.getLottoSize());
    }

    public LottoResult generateLottoResult(Lottos lottos) {
        return lottoResult.generate(lottos, winningNumbers, bonusNumber);
    }

    private void validateDuplicateBonusNumber(List<Integer> winningNumbers, Integer bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_BONUS_NUMBER);
        }
    }
}
