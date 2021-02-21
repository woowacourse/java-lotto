package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoBallTest {

    @DisplayName("LottoBall 생성 테스트")
    @Test
    void LottoBallConstructorTest() {
        assertThatCode(() -> LottoBall.valueOf(1))
                .doesNotThrowAnyException();
    }

    @DisplayName("LottoBall 생성시 1~45 범위 테스트")
    @ParameterizedTest()
    @ValueSource(ints = {-10, -5, 50, 55})
    void LottoBallParameterTest(int number) {
        assertThatThrownBy(() -> LottoBall.valueOf(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1~45 사이의 번호만 허용합니다.");
    }

    @DisplayName("LottoBall 싱글톤 변환 테스트")
    @Test
    void LottoBallSingletonTest() {
        for (int i = 1; i <= 45; i++) {
            LottoBall lottoBall1 = LottoBall.valueOf(i);
            LottoBall lottoBall2 = LottoBall.valueOf(i);
            assertThat(lottoBall1).isSameAs(lottoBall2);
            assertThat(lottoBall1).isEqualTo(lottoBall2);
        }
    }
}
