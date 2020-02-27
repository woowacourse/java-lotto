package lotto;

import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @Test
    @DisplayName("로또 결과의 초기값 0인지 확인")
    void initialValueOfLottoResultTest() {
        LottoResult lottoResult = new LottoResult();
        for (LottoRank rank : LottoRank.values()) {
            assertThat(lottoResult.getRankCount(rank)).isEqualTo(0);
        }
    }

    @Test
    @DisplayName("로또 매칭 계산 후 당첨 결과 누적 테스트")
    void accumulateLottoResultAfterCountingWinningNumbersTest() {
        String[] winningNumbers = {"1", "2", "3", "5", "4", "6"};
        String bonusNumber = "7";
        WinningNumber winningNumber = new WinningNumber();
        winningNumber.inputWinningNumbers(winningNumbers);
        winningNumber.inputBonusNumber(bonusNumber);

        String[] inputLottoNumbers = {"1", "2", "3", "4", "5", "7"};
        Lotto lotto = LottoFactory.createOneManualLotto(inputLottoNumbers);
        LottoBundle lottoBundle = new LottoBundle();
        lottoBundle.addLotto(lotto);

        LottoResult lottoResult = new LottoResult();
        lottoResult.countWinningLotto(lottoBundle, winningNumber);
        assertThat(lottoResult.getRankCount(LottoRank.SECOND)).isEqualTo(1);
    }

    @Test
    @DisplayName("로또 결과 계산 이후 최종 수익 계산")
    void calculateTotalProfitUsingResultTest() {
        String[] winningNumbers = {"1", "2", "3", "5", "4", "6"};
        String bonusNumber = "7";
        WinningNumber winningNumber = new WinningNumber();
        winningNumber.inputWinningNumbers(winningNumbers);
        winningNumber.inputBonusNumber(bonusNumber);

        String[] inputLottoNumbers = {"1", "2", "3", "4", "5", "7"};
        Lotto lotto = LottoFactory.createOneManualLotto(inputLottoNumbers);
        LottoBundle lottoBundle = new LottoBundle();
        lottoBundle.addLotto(lotto);

        LottoResult lottoResult = new LottoResult();
        lottoResult.countWinningLotto(lottoBundle, winningNumber);
        int totalProfit = lottoResult.calculateTotalProfit();
        assertThat(totalProfit).isEqualTo(30_000_000);
    }
}
