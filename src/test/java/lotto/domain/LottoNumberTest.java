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

    private static final int LOTTO_MIN_RANGE = 1;
    private static final int LOTTO_MAX_RANGE = 45;
    private static final String ERROR_WRONG_LOTTO_NUMBER = "[ERROR] " + LOTTO_MIN_RANGE + "~" + LOTTO_MAX_RANGE + " 사이의 숫자를 입력해주세요.";

    @Nested
    @DisplayName("정해진 범위 내의 숫자라면")
    class right_range {
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 45})
        @DisplayName("정상적으로 생성된다.")
        void create(final int number) {
            assertThatCode(() -> LottoNumber.from(number)).doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("정해진 범위 밖에 숫자라면")
    class wrong_range {
        @ParameterizedTest
        @ValueSource(ints = {-1, 0, 46})
        @DisplayName("에러를 출력한다.")
        void returns_error(final int number) {
            assertThatThrownBy(() -> LottoNumber.from(number))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_WRONG_LOTTO_NUMBER);
        }
    }

    @Nested
    @DisplayName("숫자가 아니라 문자로 입력이 들어오면")
    class not_integer {
        @Test
        @DisplayName("에러를 출력한다.")
        void returns_error() {
            assertThatThrownBy(() -> LottoNumber.from("aki"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_WRONG_LOTTO_NUMBER);
        }
    }

    @Nested
    @DisplayName("서로 같은 번호라면")
    class if_input_same_number {
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 45})
        @DisplayName("동일한 인스턴스이다.")
        void same_number_is_true(final int number) {
            LottoNumber number1 = LottoNumber.from(number);
            LottoNumber number2 = LottoNumber.from(number);
            assertThat(number1).isSameAs(number2);
        }
    }
}
