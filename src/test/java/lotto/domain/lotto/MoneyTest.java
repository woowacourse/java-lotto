package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.factory.MoneyFactory;

public class MoneyTest {

    @Nested
    @DisplayName("구입금액은")
    class NewMoney {

        @Nested
        @DisplayName("1000 ~ 20억 사이의 값이 주어지면")
        class Context_with_range_1000_to_2billions {

            @ParameterizedTest
            @CsvSource(value = {"1000|1000", "2000000000|2000000000"}, delimiter = '|')
            @DisplayName("객체를 생성한다.")
            void it_create_ok(String value, int expected) {
                Money money = MoneyFactory.valueOf(value);

                assertThat(money.getValue()).isEqualTo(expected);
            }
        }

        @Nested
        @DisplayName("1000 ~ 20억 사이의 값이 아닌 경우")
        class Context_without_range_1000_to_2billions {

            @ParameterizedTest
            @ValueSource(strings = {"999", "2000000001"})
            @DisplayName("예외를 발생시킨다.")
            void it_throw_exception(String value) {
                assertThatThrownBy(() -> MoneyFactory.valueOf(value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("1000부터 20억의 숫자여야 합니다.");
            }
        }

        @Nested
        @DisplayName("숫자가 아닌 값이 주어지면")
        class Context_with_not_a_number {

            @ParameterizedTest
            @ValueSource(strings = {"a", "%%", "", " "})
            @DisplayName("예외를 발생시킨다.")
            void it_throw_exception(String value) {
                assertThatThrownBy(() -> MoneyFactory.valueOf(value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("숫자여야 합니다.");
            }
        }

        @Nested
        @DisplayName("1000으로 나누어지는 값이 주어지면")
        class Context_with_divided_by_1000 {

            @ParameterizedTest
            @CsvSource(value = {"1000|1000", "2000|2000"}, delimiter = '|')
            @DisplayName("객체를 생성한다.")
            void it_create_ok(String value, int expected) {
                Money money = MoneyFactory.valueOf(value);

                assertThat(money.getValue()).isEqualTo(expected);
            }
        }

        @Nested
        @DisplayName("1000으로 나누어지는 값이 아니라면")
        class Context_with_not_divided_by_1000 {

            @ParameterizedTest
            @ValueSource(strings = {"1001", "2100"})
            @DisplayName("예외를 발생시킨다.")
            void it_throw_exception(String value) {
                assertThatThrownBy(() -> MoneyFactory.valueOf(value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("1000으로 나누어 떨어져야 합니다.");
            }
        }
    }

    @Nested
    @DisplayName("살 수 있는 로또 개수를 알려주는 기능은")
    class countToBuyLotto {
        @Test
        @DisplayName("살 수 있는 로또 개수를 알려준다.")
        void it_returns_count_to_buy_lotto() {
            Money money = new Money(5000);
            assertThat(money.countToBuyLotto().value()).isEqualTo(5);
        }
    }

    @Nested
    @DisplayName("입력한 수 만큼 로또를 살 수 있는지 알려주는 기능은")
    class validateBuyableLottoCount {
        @Nested
        @DisplayName("살 수 없는 개수가 주어지면")
        class Context_with_Count_to_cant_buy {
            @Test
            @DisplayName("예외를 발생시킨다.")
            void it_throw_exception() {
                Money money = new Money(5000);
                assertThatThrownBy(() -> money.validateBuyableLottoCount(new Count(6)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("5장 까지 살 수 있습니다.");
            }
        }
    }
}
