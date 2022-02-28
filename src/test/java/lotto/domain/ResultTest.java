package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ResultTest {

    @Nested
    @DisplayName("결과는")
    class NewResult {

        @Nested
        @DisplayName("생성될 때")
        class Context_with_empty {

            @Test
            @DisplayName("순위 별 개수를 0으로 초기화한다.")
            void it_create_ok() {
                Result result = new Result();

                for (Rank rank : Rank.values()) {
                    assertThat(result.getCount(rank)).isEqualTo(0);
                }
            }
        }
    }

    @Nested
    @DisplayName("등수의 개수를 올리는 메소드는")
    class AddCount {

        @Nested
        @DisplayName("WinningPrice가 주어지면")
        class Context_with_winning_price {

            @Test
            @DisplayName("해당 WinningPrice의 개수가 1 올라간다.")
            void it_add_count() {
                Result result = new Result();

                result.add(Rank.First);

                assertThat(result.getCount(Rank.First)).isEqualTo(1);
            }
        }

        @Nested
        @DisplayName("같은 WinningPrice가 2개 주어지면")
        class Context_with_tow_winning_price {

            @Test
            @DisplayName("해당 WinningPrice의 개수가 2 올라간다.")
            void it_add_count() {
                Result result = new Result();

                result.add(Rank.First);
                result.add(Rank.First);

                assertThat(result.getCount(Rank.First)).isEqualTo(2);
            }
        }
    }

    @Nested
    @DisplayName("수익률을 계산하는 메소드는")
    class GetRateOfProfit {

        @Nested
        @TestInstance(Lifecycle.PER_CLASS)
        @DisplayName("돈이 주어지면")
        class Context_with_money {

            @ParameterizedTest
            @MethodSource("provideSource")
            @DisplayName("당첨금액의 합과 비교하여 수익률을 반환한다.")
            void it_returns_rate_of_profit(int money, double expected) {
                Result result = new Result();
                result.add(Rank.Fifth);
                assertThat(result.getRateOfProfit(new Money(money))).isEqualTo(expected);
            }

            Stream<Arguments> provideSource() {
                return Stream.of(
                        Arguments.of(10000, 0.5),
                        Arguments.of(5000, 1.0),
                        Arguments.of(50000, 0.1),
                        Arguments.of(1000, 5.0),
                        Arguments.of(1000000, 0.005),
                        Arguments.of(10000000, 0.001),
                        Arguments.of(100000000, 0.0)
                );
            }
        }
    }
}
