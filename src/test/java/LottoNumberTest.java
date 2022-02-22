import domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    @DisplayName("로또 번호 범위가 아니면 예외")
    void lottoNumberRangeException(int source) {
        assertThatThrownBy(() -> LottoNumber.from(source))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    @DisplayName("로또 번호 범위가 아니면 예외")
    void lottoNumberCreate(int source) {
        assertDoesNotThrow(() -> LottoNumber.from(source));
    }
}
