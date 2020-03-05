package lotto;

import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    private int[] winningNumbers = {1, 2, 3, 4, 5, 6};
    private int bonusNumber = 7;
    private WinningNumber winningNumber = new WinningNumber(winningNumbers, bonusNumber);
    private int[] inputLottoNumbers = {1, 2, 3, 4, 5, 7};
    private LottoGenerator lottoGenerator = new ManualLottoGenerator(inputLottoNumbers);
    private Lotto lotto = lottoGenerator.generateLotto();
    private Lottos lottos = new Lottos();

    @Test
    @DisplayName("로또 매칭 계산 후 당첨 결과 누적 테스트")
    void accumulateLottoResultAfterCountingWinningNumbersTest() {
        lottos.addLotto(lotto);
        LottoResult lottoResult = new LottoResult(lottos, winningNumber);
        assertThat(lottoResult.getRankCount(LottoRank.SECOND)).isEqualTo(1);
    }

    @Test
    @DisplayName("로또 결과 계산 이후 최종 수익 계산")
    void calculateTotalProfitUsingResultTest() {
        lottos.addLotto(lotto);
        LottoResult lottoResult = new LottoResult(lottos, winningNumber);
        int totalProfit = lottoResult.calculateTotalProfit();
        assertThat(totalProfit).isEqualTo(30_000_000);
    }
}
