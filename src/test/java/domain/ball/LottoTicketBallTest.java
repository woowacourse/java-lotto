package domain.ball;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;

class LottoTicketBallTest {

    @DisplayName("LottoBall 정상 생성된다.")
    @Test
    void LottoBall_생성_테스트() {
        assertThatCode(() -> new LottoBall(3))
                .doesNotThrowAnyException();
    }

    @DisplayName("보장되지 않은 LottoNumber 에러 발생한다. ")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46, 47})
    void LottoBall_에러_테스트(int value) {
        Assertions.assertThatThrownBy(() -> new LottoBall(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}