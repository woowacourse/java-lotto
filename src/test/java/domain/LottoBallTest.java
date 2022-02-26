package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoBallTest {

    @Test
    @DisplayName("같은 숫자를 가진 로또 볼 객체는 동일하다.")
    void lottoNumberEqual() {
        LottoBall lottoBall1 = new LottoBall(12);
        LottoBall lottoBall2 = new LottoBall(12);

        assertThat(lottoBall1).isEqualTo(lottoBall2);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    @DisplayName("1~45 범위 밖의 수로 생성하면 예외 발생")
    void outOfLottoRange_ThrowException(int number) {
        assertThatThrownBy(() -> new LottoBall(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
