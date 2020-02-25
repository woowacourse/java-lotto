package lotto.domain.ticket.ball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoBallTest {

    @DisplayName("예외 테스트: 로또 범위 이외의 숫자로 LottoBall 생성하는 경우 Exception 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void test1(int number) {
        assertThatThrownBy(() -> {
            new LottoBall(number);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("%d: 로또 범위 이외 숫자입니다", number);
    }

    @DisplayName("같은 번호의 LottoBall 이 같은 LottoBall 인지 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void test2(int number) {
        assertThat(new LottoBall(number)).isEqualTo(new LottoBall(number));
    }
}
