package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoCountTest {

    @DisplayName("LottoCount 정상 생성 테스트")
    @Test
    void LottoCountTest() {
        LottoCount lottoCount = new LottoCount(2, new Money(3000));
        assertThat(lottoCount).isInstanceOf(LottoCount.class);
    }

    @DisplayName("수동로또 초과 생성 테스트")
    @Test
    void LottoCountExcessTest() {
        assertThatThrownBy(() -> new LottoCount(5, new Money(3000)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("자동로또 갯수 확인 테스트")
    @Test
    void LottoCountConfirmTest() {
        LottoCount lottoCount = new LottoCount(2, new Money(3000));
        assertThat(lottoCount.getAutoLottoCount()).isEqualTo(1);
    }
}
