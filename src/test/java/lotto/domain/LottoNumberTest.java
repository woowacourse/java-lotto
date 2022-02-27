package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Nested
@DisplayName("로또 번호가")
class LottoNumberTest {
    @Nested
    @DisplayName("정해진 범위 내의 숫자라면")
    class right_range {
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 45})
        @DisplayName("정상적으로 생성된다.")
        void create(final int number) {
            assertThatCode(() -> new LottoNumber(number)).doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("정해진 범위 밖에 숫자라면")
    class wrong_range {
        @ParameterizedTest
        @ValueSource(ints = {-1, 0, 46})
        @DisplayName("에러를 출력한다.")
        void returns_error(final int number) {
            assertThatThrownBy(() -> new LottoNumber(number))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }
    }

    @Nested
    @DisplayName("숫자가 아니라 문자로 입력이 들어오면")
    class not_integer {
        @Test
        @DisplayName("에러를 출력한다.")
        void returns_error() {
            assertThatThrownBy(() -> new LottoNumber("aki"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }
    }
}
