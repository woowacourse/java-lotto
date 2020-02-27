package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {-100, 0, 46, 1000})
    @DisplayName("로또 번호가 범위 밖인 경우")
    void LottoNumberNullOrEmpty(int expected) {
        assertThatThrownBy(() -> new LottoNumber(expected))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위를 벗어");
    }
}
