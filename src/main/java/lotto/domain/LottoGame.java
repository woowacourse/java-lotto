package lotto.domain;

import lotto.domain.number.LottoRound;
import lotto.domain.number.LottoRounds;
import lotto.domain.number.LottoRoundsGenerator;
import lotto.domain.number.WinningNumbers;
import lotto.domain.result.GameResults;
import lotto.domain.result.Money;

import java.util.List;

public class LottoGame {
    public static LottoRounds createLottoRounds(Money money, List<LottoRound> manualLottos) {
        LottoRoundsGenerator lottoRoundsGenerator =
                LottoRoundsGenerator.createConfiguratedLottoRoundsGenerator(manualLottos);
        return lottoRoundsGenerator.generate(money);
    }

    public static GameResults calculateResult(LottoRounds lottoRounds, WinningNumbers winnngNumbers) {
        return lottoRounds.calculateGameResult(winnngNumbers);
    }

    public static double calculateYield(Money money, GameResults gameResults) {
        return gameResults.calculateYield(money);
    }
}
