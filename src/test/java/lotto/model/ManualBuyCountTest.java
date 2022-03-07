package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.model.ticket.buy.ManualBuyCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ManualBuyCountTest {

    @Test
    @DisplayName("수동으로 구매할 로또 수를 생성한다.")
    public void create() {
        // given
        ManualBuyCount manualBuyCount = ManualBuyCount.of(3);

        // then
        assertThat(manualBuyCount).isNotNull();
    }

    @Test
    @DisplayName("수동으로 구매할 로또 수가 음수면 예외를 반환한다.")
    public void validateNone() {
        assertThatThrownBy(() -> {
            ManualBuyCount manualBuyCount = ManualBuyCount.of(-1);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("수동으로 구매할 로또 수는 0이상의 수여야 합니다");
    }
}
