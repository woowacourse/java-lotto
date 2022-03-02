package lotto.model.lotto;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.model.result.Rank;
import lotto.model.result.RateOfReturn;
import lotto.model.result.WinningResult;
import lotto.model.winningnumber.WinningLotto;
import lotto.model.winningnumber.WinningLottoResponse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoGameTest {
    private final List<String> numbers = List.of("1", "2", "3", "4", "5", "6");

    @Test
    @DisplayName("수동 로또 개수가 없는 경우")
    void checkLottoCount() {
        LottoGame lottoGame = new LottoGame();

        ManualCount manualCount = new ManualCount("1");
        List<Lotto> lottos = new ArrayList<>();
        Lotto lotto = new Lotto(numbers);
        lottoGame.makeManualLottos(lottos, lotto, manualCount);

        assertThat(lottoGame.isPossibleMakeLottos(manualCount)).isTrue();
    }

    @Test
    @DisplayName("로또 생성 테스트")
    void checkCreateLotto() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(numbers));
        LottoCount lottoCount = new LottoCount("10000", 1);
        LottoGame lottoGame = new LottoGame();

        assertThat(lottoGame.makeLottos(lottoCount, lottos)
                .getLottoStorage()
                .size())
                .isEqualTo(10);
    }

    @Test
    @DisplayName("수익률 검증 테스트")
    void checkRateOfReturn() {
        LottoGame lottoGame = new LottoGame();
        RateOfReturn rateOfReturn = lottoGame.storeMoneyInRateOfReturn("10000");

        WinningResult winningResult = new WinningResult();
        winningResult.addCount(Rank.FOUR);
        assertThat(lottoGame.sendRateOfReturn(rateOfReturn, winningResult)).isEqualTo(5.0);
    }

    @Test
    @DisplayName("로또와 당첨 번호 비교 결과 테스트")
    void compareLottoWithWinning() {
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(numbers));
        LottoStorage lottoStorage = new LottoStorage(new LottoCount("1000", 0), lottos);
        LottoGame lottoGame = new LottoGame();
        WinningLottoResponse winningLottoResponse =
                new WinningLottoResponse(new WinningLotto(numbers, "7"));

        assertThat(lottoGame.calcWinningNumber(lottoStorage, winningLottoResponse)
                .getWinningCount()
                .get(Rank.SIX))
                .isEqualTo(1);
    }
}
