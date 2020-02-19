package lotto.domain.result;

import lotto.domain.result.GameResult;

public interface GameResultStrategy {
    public boolean calculate(int correctLottoNumberSize, boolean isCorrectBonusNumber);
}
