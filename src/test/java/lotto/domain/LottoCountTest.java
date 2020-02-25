package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoCountTest {

    @DisplayName("로또 갯수는 1보다 커야함")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void validateCount(int count) {
        assertThatThrownBy(() -> new LottoCount(count))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수동 로또 갯수는 0보다 커야함")
    @ParameterizedTest
    @CsvSource(value = {"1, -1", "2, -2"})
    void validateManualCount(int total, int manual) {
        assertThatThrownBy(() -> new LottoCount(total, manual))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
