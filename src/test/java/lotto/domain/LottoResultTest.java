package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {
    private List<PrizeType> prizeTypes;
    private LottoResult lottoResult;

    @BeforeEach
    void init() {
        this.prizeTypes = Arrays.asList(PrizeType.FIRST_PRIZE, PrizeType.FIRST_PRIZE, PrizeType.NO_PRIZE);
        this.lottoResult = new LottoResult(prizeTypes);
    }

    @Test
    @DisplayName("해당 당첨 티켓이 몇개 있는 지 확인하는 기능")
    void getCountByPrizeType() {
        assertThat(lottoResult.getCountByPrizeType(PrizeType.FIRST_PRIZE)).isEqualTo(2);
    }

    @Test
    @DisplayName("수익률 계산 확인")
    void calculateProfitRate() {
        Money money = new Money(2000);
        assertThat(lottoResult.calculateProfitRate(money)).isEqualTo(2000000.0);
    }
}