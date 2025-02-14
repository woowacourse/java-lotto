package lotto.model.lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @DisplayName("로또 번호 범위를 벗어난 번호는 뽑아지지 않는다.")
    @ValueSource(ints = {0, 46})
    @ParameterizedTest
    void drawNumberInOutOfRange(int number) {
        assertThatThrownBy(() -> LottoNumber.draw(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호 범위를 만족하면 해당 로또 번호가 뽑아진다.")
    @ValueSource(ints = {1, 45})
    @ParameterizedTest
    void drawNumberInRange(int number) {
        assertDoesNotThrow(() -> LottoNumber.draw(number));
    }

}
