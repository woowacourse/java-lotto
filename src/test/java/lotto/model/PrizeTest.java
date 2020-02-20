package lotto.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.model.Prize.countYieldMoney;
import static org.assertj.core.api.Assertions.assertThat;

public class PrizeTest {

    @DisplayName("총 상금 계산")
    @Test
    void setPrize() {
        LottoResultMap.resultCount.get("4").setCount();
        new Prize();
        assertThat(Prize.getPrize()).isEqualTo(50000);
    }

    @Test
    @DisplayName("수익률 확인")
    void yieldCorrectTest() {
        new Payment("14000");
        LottoResultMap.resultCount.get("3").setCount();
        new Prize();
        assertThat(countYieldMoney()).isEqualTo(35);
    }
}
