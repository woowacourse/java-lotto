package lotto.domain;

import lotto.domain.number.LottoRound;
import lotto.domain.number.LottoRounds;
import lotto.domain.number.LottoRoundsGenerator;
import lotto.domain.number.WinningNumbers;
import lotto.domain.result.GameResult;
import lotto.domain.result.GameResults;
import lotto.domain.result.Money;

import java.util.List;

public class LottoGame {
    private final Money money;
    private final LottoRounds lottoRounds;

    private LottoGame(Money money, LottoRounds lottoRounds) {
        this.money = money;
        this.lottoRounds = lottoRounds;
    }

    public static LottoGame initialize(Money money, List<LottoRound> manualLottos) {
        LottoRounds lottoRounds = createLottoRounds(money, manualLottos);
        return new LottoGame(money, lottoRounds);
    }

    private static LottoRounds createLottoRounds(Money money, List<LottoRound> manualLottos) {
        List<LottoRound> lottoRoundList = LottoRoundsGenerator.createLottoRounds(money, manualLottos);
        return new LottoRounds(lottoRoundList);
    }

    public GameResults calculateResult(WinningNumbers winnngNumbers) {
        List<GameResult> gameResults = lottoRounds.calculateGameResult(winnngNumbers);
        return new GameResults(gameResults);
    }

    // 데미테르의 법칙을 준수하기 위해 래핑한 메서드입니다.
    // MoneyTest에 Test 메서드를 작성해놨습니다.
    // 해당 클래스의 테스트 코드에서도 해당 메서드를 테스트하느 메서드를 추가해야할지 여쭙고싶습니다!
    public double calculateYield(GameResults gameResults) {
        return money.calculateYield(gameResults);
    }

    public LottoRounds getLottoRounds() {
        return lottoRounds;
    }
}
