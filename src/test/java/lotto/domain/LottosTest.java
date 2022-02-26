package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class LottosTest {

    @Nested
    @DisplayName("Lottos는")
    class NewLottos {

        @Nested
        @DisplayName("구입금액이 주어지면")
        class Context_with_money {

            @ParameterizedTest
            @CsvSource(value = {"1000|1", "2000|2"}, delimiter = '|')
            @DisplayName("구입금액에 맞는 개수의 로또를 생성한다.")
            void It_create_lottos(int value, int expected) {
                Lottos lottos = new Lottos(new Money(value));

                assertThat(lottos.getCount()).isEqualTo(expected);
            }
        }
    }

    @Nested
    @DisplayName("진행 결과를 구하는 메서드는")
    class GetResult {

        @Nested
        @TestInstance(Lifecycle.PER_CLASS)
        @DisplayName("당첨 번호가 주어지면")
        class Context_with_money {

            @ParameterizedTest
            @MethodSource("provideSource")
            @DisplayName("Result를 반환한다.")
            void It_create_result(Lotto lotto, WinningPrice expected) {
                final Lottos lottos = new Lottos(List.of(lotto));
                final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
                winningNumbers.initBonusNumber(7);

                final Result result = lottos.getResult(winningNumbers);

                assertThat(result.getCount(expected)).isEqualTo(1);
            }

            Stream<Arguments> provideSource() {
                return Stream.of(
                        Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), WinningPrice.All),
                        Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)), WinningPrice.FiveAndBonus),
                        Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 45)), WinningPrice.Five),
                        Arguments.of(new Lotto(List.of(1, 2, 3, 4, 44, 45)), WinningPrice.Four),
                        Arguments.of(new Lotto(List.of(1, 2, 3, 43, 44, 45)), WinningPrice.Three)
                );
            }
        }
    }
}
