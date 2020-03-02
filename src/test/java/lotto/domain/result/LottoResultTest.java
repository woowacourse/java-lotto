package lotto.domain.result;

import lotto.domain.Buyer;
import lotto.domain.lottoTicket.LottoAmount;
import lotto.domain.lottoTicket.WinningLotto;
import lotto.util.ConvertInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    Buyer buyer;
    WinningLotto winningLotto;
    LottoResult lottoResult;

    @BeforeEach
    void setUp() {
        buyer = new Buyer(
                Arrays.asList(
                        "1, 2, 3, 4, 5, 6",
                        "1, 2, 3, 4, 5, 7",
                        "1, 2, 3, 4, 5, 9",
                        "10, 20, 31, 41, 11, 9"
                )
                , new LottoAmount(4, 4)
        );
        winningLotto = new WinningLotto(ConvertInput.convertLottoNumbers("1, 2, 3, 4, 5, 6"), 7);
        lottoResult = new LottoResult(buyer, winningLotto);
    }

    @Test
    @DisplayName("등수 계산")
    void checkRankTest() {

        assertThat(lottoResult.getLottoResult().get(WinningValue.FIRST)).isEqualTo(1);
        assertThat(lottoResult.getLottoResult().get(WinningValue.SECOND)).isEqualTo(1);
        assertThat(lottoResult.getLottoResult().get(WinningValue.THIRD)).isEqualTo(1);
        assertThat(lottoResult.getLottoResult().get(WinningValue.FORTH)).isEqualTo(0);
    }

    @Test
    @DisplayName("수익률 계산")
    void calculateRewardRateTest() {
        assertThat(lottoResult.calculateRewardRate(25000))
                .isEqualTo(8126000);
    }
}
