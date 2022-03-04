package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ManualCountTest {

    @DisplayName("수동 구매 개수가 정상적으로 생성되는지 확인")
    @Test
    void createManualCount() {
        // given
        ManualCount manualCount = new ManualCount(4);
        // then
        assertThat(manualCount).isNotNull();
    }

    @Test
    void validatePositive() {
        // given
        Money money = new Money(3000);
        int manualCount = -1;
        // then
        assertThatThrownBy(() -> ManualCount.of(money, manualCount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("수동 구매 개수는 0이상의 정수만 가능합니다.");
    }

    @DisplayName("구입금액보다 더 많은 수동 구매가 이뤄지면 예외 발생")
    @Test
    void validateLessThenMoney() {
        // given
        Money money = new Money(3000);
        int manualCount = 4;
        // then
        assertThatThrownBy(() -> ManualCount.of(money, manualCount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("수동 구매 개수는 총 구매 개수 이내에서만 구매 가능합니다.");
    }

    @DisplayName("수동 구매 개수가 정상적으로 반환하는지 확인")
    @Test
    void getCount() {
        // given
        ManualCount manualCount = new ManualCount(4);
        // then
        assertThat(manualCount.getCount()).isEqualTo(4);
    }
}
