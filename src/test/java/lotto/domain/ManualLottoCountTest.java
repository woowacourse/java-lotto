package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ManualLottoCountTest {
    @Test
    @DisplayName("유효한 개수")
    public void correct() {
        // given
        int max = 10;
        int tryCount = 9;

        // when

        // then
        assertDoesNotThrow(() -> new ManualLottoCount(tryCount, max));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 100})
    @DisplayName("숫자가 범위 내에 존재하지 않는 경우 예외 발생")
    void incorrect(int tryCount) {
        // given
        int max = 10;

        // then
        assertThatThrownBy(() -> new ManualLottoCount(tryCount, max)).isInstanceOf(IllegalArgumentException.class);
    }
}