package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoBallTest {

    @DisplayName("생성된 ball들이 범위내의 숫자인지")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void test1(int number) {
        assertThatThrownBy(() -> {
            new LottoBall(number);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력값: " + number + ": 범위 이외 숫자");
    }

    @DisplayName("볼 생성시 번호가 같은지 확인")
    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void test2(int number) {
        assertThat(new LottoBall(number)).isEqualTo(new LottoBall(number));
    }

}
