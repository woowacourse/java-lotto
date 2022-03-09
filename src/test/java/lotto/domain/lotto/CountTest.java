package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CountTest {
    @Nested
    @DisplayName("객체를 생성할 때")
    class New {
        @Nested
        @DisplayName("0 이상의 숫자가 주어지면")
        class Context_with_not_negative_number {
            @Test
            @DisplayName("객체를 생성한다.")
            void it_returns_count() {
                Assertions.assertDoesNotThrow(() -> new Count(0));
            }
        }

        @Nested
        @DisplayName("음수가 주어지면")
        class Context_with_negative_number {
            @Test
            @DisplayName("예외를 발생시킨다.")
            void it_throw_exception() {
                assertThatThrownBy(() -> new Count(-1))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("0 이상의 값이어야 합니다.");
            }
        }
    }

    @Nested
    @DisplayName("빼기 연산 기능은")
    class Subtract {
        @Nested
        @DisplayName("자신의 객체보다 작은 값의 Count가 주어지면")
        class Context_with_small_count {
            @Test
            @DisplayName("Count의 수를 뺀 Count를 반환한다.")
            void it_returns_subtract_count() {
                Count count10 = new Count(10);
                Count count5 = new Count(5);
                assertThat(count10.subtract(count5).value()).isEqualTo(5);
            }
        }

        @Nested
        @DisplayName("자신의 객체보다 큰 값의 Count가 주어지면")
        class Context_with_big_count {
            @Test
            @DisplayName("예외를 발생시킨다.")
            void it_returns_subtract_count() {
                Count count10 = new Count(10);
                Count count5 = new Count(5);
                assertThatThrownBy(() -> count5.subtract(count10))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("5보다 작은 값이어야 합니다.");
            }
        }
    }
}
