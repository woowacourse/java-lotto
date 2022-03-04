package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Nested
@DisplayName("수동으로 구매할 로또 수는")
public class ManualLottoCountTest {

    private static final String ERROR_WRONG_INPUT_NUMBER = "[ERROR] 수동으로 구매할 로또 수는 구입금액 내에서 구매가능한 0 이상의 수여야 합니다.";

    @Nested
    @DisplayName("숫자가 아닌 문자로 입력이 들어온다면")
    class if_input_not_number {
        @Test
        @DisplayName("에러를 출력한다.")
        void returns_error() {
            assertThatThrownBy(() -> new ManualLottoCount("value", 10))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_WRONG_INPUT_NUMBER);
        }
    }

    @Nested
    @DisplayName("음수가 들어온다면")
    class if_input_negative {
        @Test
        @DisplayName("에러를 출력한다.")
        void returns_error() {
            assertThatThrownBy(() -> new ManualLottoCount("-1", 10))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_WRONG_INPUT_NUMBER);
        }
    }

    @Nested
    @DisplayName("구매가능한 범위를 초과하는 수가 들어온다면")
    class if_input_more_than_max {
        @Test
        @DisplayName("에러를 출력한다.")
        void returns_error() {
            assertThatThrownBy(() -> new ManualLottoCount("20", 10))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ERROR_WRONG_INPUT_NUMBER);
        }
    }

    @Nested
    @DisplayName("구매가능한 범위 내의 수가 들어온다면")
    class if_inpu_right_amount {
        @Test
        @DisplayName("정상적으로 생성된다.")
        void returns_error() {
            assertThatCode(() -> new ManualLottoCount("10", 10)).doesNotThrowAnyException();
        }
    }
}
