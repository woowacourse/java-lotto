package model;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @ParameterizedTest
    @DisplayName("로또 번호가 1 ~ 45 사이에 있지 않은 경우 테스트")
    @ValueSource(ints = {0, 46})
    void throwExceptionWhenInvalidRange(int number) {
        assertThatCode(() -> LottoNumber.of(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1 ~ 45 사이여야 합니다.");
    }

    @ParameterizedTest
    @DisplayName("로또 번호가 1 ~ 45 사이에 있는 경우 테스트")
    @ValueSource(ints = {1, 45})
    void checkValidLottoNumberRangeTest(int number) {
        assertThatCode(() -> LottoNumber.of(number))
                .doesNotThrowAnyException();
    }
}
