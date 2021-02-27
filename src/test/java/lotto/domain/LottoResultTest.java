package lotto.domain;

import lotto.domain.result.LottoResult;
import lotto.domain.result.Prize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {
    private List<Prize> prizes;
    private LottoResult lottoResult;

    @BeforeEach
    void init() {
        this.prizes = Arrays.asList(Prize.FIRST_PRIZE, Prize.FIRST_PRIZE, Prize.NO_PRIZE, Prize.NO_PRIZE);
        this.lottoResult = new LottoResult(prizes);
    }

    @Test
    @DisplayName("해당 당첨 티켓이 몇개 있는 지 확인하는 기능")
    void getCountByPrizeType() {
        assertThat(lottoResult.getCountPerPrizeType(Prize.FIRST_PRIZE)).isEqualTo(2);
    }

    @Test
    @DisplayName("수익금 계산 확인")
    void calculateProfitRate() {
        assertThat(lottoResult.getTotalProfit().getValue()).isEqualTo(4000000000L);
    }
}