package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46})
    @DisplayName("로또 번호가 1보다 작거나 45보다 크면 예외 발생")
    void generateLottoNumberTest(int number) {
        assertThatThrownBy(
                () -> LottoNumber.generateLottoNumber(number)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}