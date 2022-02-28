package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Nested
@DisplayName("구입금액이")
class MoneyTest {
    @Nested
    @DisplayName("숫자가 아닌 문자로 입력이 들어온다면")
    class if_input_int {
        @Test
        @DisplayName("에러를 출력한다.")
        void returns_error() {
            assertThatThrownBy(() -> new Money("value"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }
    }

    @Nested
    @DisplayName("로또 한장 가격 미만의 금액이라면")
    class if_input_low_money {
        @Test
        @DisplayName("에러를 출력한다.")
        void returns_error() {
            assertThatThrownBy(() -> new Money("500"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }
    }

    @Nested
    @DisplayName("로또 한장 가격으로 나누어떨어지지 않는다면")
    class if_input_not_divided {
        @Test
        @DisplayName("에러를 출력한다.")
        void returns_error() {
            assertThatThrownBy(() -> new Money("2500"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }
    }

    @Nested
    @DisplayName("로또 한장 가격으로 나누어떨어진다면")
    class if_input_right {
        @Test
        @DisplayName("정상적으로 생성된다.")
        void create() {
            assertThatCode(() -> new Money("14000")).doesNotThrowAnyException();
        }
    }
}
