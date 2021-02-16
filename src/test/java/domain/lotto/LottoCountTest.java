package domain.lotto;

import domain.budget.Budget;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoCountTest {

    private static final Budget DUMMY_BUDGET = new Budget(new BigDecimal(10000));

    @DisplayName("LottoCount 정상 생성된다.")
    @Test
    void LottoCount_생성_테스트() {
        //given
        LottoCount lottoCount = LottoCount.of(DUMMY_BUDGET);

        //when

        //then
        assertThat(lottoCount.getLottoCount()).isEqualTo(10);
    }

    @DisplayName("LottoCount가 0일시 에러 출력한다.")
    @Test
    void LottoCount_생성_0일시_테스트() {
        assertThatThrownBy(() -> LottoCount.of(new Budget(new BigDecimal(0))))
                .isInstanceOf(IllegalArgumentException.class);
    }
}