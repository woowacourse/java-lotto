package lotto;

import domain.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    @Test
    void 로또_결과의_초기값_확인() {
        LottoResult lottoResult = new LottoResult();
        for (LottoRank rank : LottoRank.values()) {
            assertThat(lottoResult.getRankCount(rank)).isEqualTo(0);
        }
    }

    @Test
    void 당첨결과_누적_테스트() {
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
    void 총_수익_계산_테스트() {
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
