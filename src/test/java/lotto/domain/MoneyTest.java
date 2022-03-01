package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @Nested
    @DisplayName("구입금액은")
    class NewMoney {

        @Nested
        @DisplayName("1000 ~ 20억 사이의 값이 아닌 경우")
        class Context_without_range_1000_to_2billions {

            @ParameterizedTest
            @ValueSource(strings = {"999", "2000000001"})
            @DisplayName("예외를 발생시킨다.")
            void it_throw_exception(int value) {
                assertThatThrownBy(() -> new Money(value))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("1000부터 20억의 숫자여야 합니다.");
            }
        }
    }

    @Nested
    @DisplayName("자동으로 생성할 로또 수를 반환하는 메서드는")
    class CalculateAmountOfAuto {

        @Nested
        @DisplayName("수동으로 구매할 로또 수와 로또의 가격이 주어지면")
        class Context_with_quantity_of_manual_and_lotto_price {

            @ParameterizedTest
            @CsvSource(value = {"0|10", "3|7", "10|0"}, delimiter = '|')
            @DisplayName("값을 반환한다.")
            void It_returns_amount_of_auto(int value, int expected) {
                final Money money = new Money(10000);

                final int quantityOfAuto = money.getQuantityOfAuto(value);
                assertThat(quantityOfAuto).isEqualTo(expected);
            }
        }
    }

    @Nested
    @DisplayName("수동으로 구매할 로또 수가 유효한지 검증하는 메서드는")
    class ValidateQuantityOfManual {

        @Nested
        @DisplayName("값이 유효하지 않으면")
        class Context_with_not_available_quantity_of_manual {

            @ParameterizedTest
            @CsvSource(value = {"-1|10000", "11|10000", "21|20000"}, delimiter = '|')
            @DisplayName("예외를 던진다.")
            void It_throws_exception(int quantityOfManual, int moneyValue) {
                final Money money = new Money(moneyValue);

                assertThatThrownBy(() -> money.validateQuantityOfManual(quantityOfManual))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("0개 이상 " + moneyValue / 1000 + "개 이하의 로또만 수동으로 구매할 수 있습니다.");
            }
        }
    }

    @Nested
    @DisplayName("수익률을 계산하는 메서드는")
    class CalculateRateOfProfit {

        @Nested
        @TestInstance(Lifecycle.PER_CLASS)
        @DisplayName("총 당첨금이 주어지면")
        class Context_with_totla_profit {

            @ParameterizedTest
            @MethodSource("provideSource")
            @DisplayName("수익률을 소수점 아래 4번째 자리에서 반올림하여 반환한다.")
            void It_returns_rate_of_profit(int value, long totalProfit, double expected) {
                final Money money = new Money(value);

                assertThat(money.calculateRateOfProfit(totalProfit)).isEqualTo(expected);
            }

            Stream<Arguments> provideSource() {
                return Stream.of(
                        Arguments.of(1_000, 5000, 5),
                        Arguments.of(10_000, 5000, 0.5),
                        Arguments.of(1_000_000, 5000, 0.005),
                        Arguments.of(10_000_000, 5000, 0.001)
                );
            }
        }
    }
}
