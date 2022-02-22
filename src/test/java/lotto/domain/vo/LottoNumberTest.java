package lotto.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumberTest {
    @ParameterizedTest
    @DisplayName("1부터 45까지의 자연수가 아닌 값으로 객체를 생성할 경우 예외를 발생시킨다.")
    @ValueSource(strings = {"0", "-1", "1.2", "a"})
    void create_exceptionByInvalidValue(final String value) {
        // given
        final String expectedExceptionMessage = "로또 번호는 1 ~ 45 사이의 자연수여야합니다.";
        // when then
        assertThatThrownBy(() -> LottoNumber.from(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }
}
