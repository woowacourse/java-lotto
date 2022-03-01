package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Nested
@DisplayName("수동으로 구매할 로또 수는")
public class ManualLottoAmountTest {
    @Nested
    @DisplayName("숫자가 아닌 문자로 입력이 들어온다면")
    class if_input_not_number {
        @Test
        @DisplayName("에러를 출력한다.")
        void returns_error() {
            assertThatThrownBy(() -> new ManualLottoAmount("value", 10))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }
    }

    @Nested
    @DisplayName("음수가 들어온다면")
    class if_input_negative {
        @Test
        @DisplayName("에러를 출력한다.")
        void returns_error() {
            assertThatThrownBy(() -> new ManualLottoAmount("-1", 10))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }
    }

    @Nested
    @DisplayName("구매가능한 범위를 초과하는 수가 들어온다면")
    class if_input_more_than_max {
        @Test
        @DisplayName("에러를 출력한다.")
        void returns_error() {
            assertThatThrownBy(() -> new ManualLottoAmount("20", 10))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }
    }

    @Nested
    @DisplayName("구매가능한 범위 내의 수가 들어온다면")
    class if_inpu_right_amount {
        @Test
        @DisplayName("정상적으로 생성된다.")
        void returns_error() {
            assertThatCode(() -> new ManualLottoAmount("10", 10)).doesNotThrowAnyException();
        }
    }
}
