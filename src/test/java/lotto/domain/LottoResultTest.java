package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class LottoResultTest {
    @DisplayName("최종 수익 계산")
    @Test
    void calculateTotalReward() {
        LottoResult lottoResult = new LottoResult();
        lottoResult.plusTicketCount(Rank.FIFTH);
        assertThat(lottoResult.calculateTotalReward()).isEqualTo(5000);
    }
}
