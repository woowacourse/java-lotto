package lotto.domain.ticket.ball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoBallValidatorTest {

    @DisplayName("로또 범위 이외의 숫자 예외 처리 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void validateNumber(int number) {
        assertThatThrownBy(() -> LottoBallValidator.validateNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("%d: 로또 범위 이외 숫자입니다", number);
    }
}
