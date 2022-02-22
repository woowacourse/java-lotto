import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberTest {

    @Nested
    @DisplayName("로또번호는")
    class NewNumber {

        @Nested
        @DisplayName("1~45의 숫자가 주어지면")
        class Context_with_Number_1_to_45 {

            @ParameterizedTest
            @ValueSource(ints = {1, 45})
            @DisplayName("객체가 생성한다.")
            void it_create_ok(int value) {
                Number number = new Number(value);

                assertThat(number.getValue()).isEqualTo(value);
            }
        }

        @Nested
        @DisplayName("1미만 45 초과의 숫자가 주어지면")
        class Context_without_Number_1_to_45 {

            @ParameterizedTest
            @ValueSource(ints = {0, 46})
            @DisplayName("예외가 발생한다.")
            void it_throw_exception(int value) {
                assertThatThrownBy(() -> new Number(value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("1부터 45의 숫자여야 합니다.");
            }
        }
    }
}
