package domain.ball;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;

class LottoBallTest {

    @DisplayName("LottoBall 정상 생성 테스트.")
    @Test
    void lottoBallGenerateTest() {
        assertThatCode(() -> new LottoBall(3))
                .doesNotThrowAnyException();
    }

    @DisplayName("보장되지 않은(중복되지 않은 1~45의 숫자이며, 6개의 숫자) LottoBall이 저장될 시 에러 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 47})
    void lottoBallNotGuaranteedErrorTest(int value) {
        Assertions.assertThatThrownBy(() -> new LottoBall(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}