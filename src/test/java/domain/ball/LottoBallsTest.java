package domain.ball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoBallsTest {

    @DisplayName("LottoBalls 정상 생성된다")
    @Test
    void LottoBalls_생성_테스트() {
        assertThatCode(() -> LottoBalls.generate(Arrays.asList(1, 2, 3, 4, 5, 6)))
                .doesNotThrowAnyException();
    }

    @DisplayName("LottoBalls의 인스턴스의 사이즈가 6이 아니면 에러가 발생한다. ")
    @Test
    void LottoBalls_사이즈_예외_테스트() {
        assertThatThrownBy(() -> LottoBalls.generate(Arrays.asList(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("LottoBalls에 중복된 LottoNumber가 있다면 에러가 발생한다.")
    @Test
    void LottoBalls_중복_예외_테스트() {
        assertThatThrownBy(() -> LottoBalls.generate(Arrays.asList(1, 2, 2, 2, 2, 2)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
