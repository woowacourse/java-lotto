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
import org.junit.jupiter.params.provider.MethodSource;

public class WinningNumbersTest {

    @Nested
    @DisplayName("당첨 순위를 알려주는 메소드는")
    class GetWinningPrice {

        @Nested
        @TestInstance(Lifecycle.PER_CLASS)
        @DisplayName("로또가 주어지면")
        class Context_with_lotto {

            @ParameterizedTest
            @MethodSource("provideSource")
            @DisplayName("당첨 순위를 알려준다.")
            void it_returns_winning_price(List<Integer> integers, WinningPrice key) {
                final Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
                final Number bonusNumber = Number.getInstance(7);
                final WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

                final Lotto lotto = new Lotto(integers);

                assertThat(winningNumbers.getWinningPrice(lotto).get()).isEqualTo(key);
            }

            Stream<Arguments> provideSource() {
                return Stream.of(
                        Arguments.of(List.of(1, 2, 3, 4, 5, 6), WinningPrice.All),
                        Arguments.of(List.of(1, 2, 3, 4, 5, 7), WinningPrice.FiveAndBonus),
                        Arguments.of(List.of(1, 2, 3, 4, 5, 44), WinningPrice.Five),
                        Arguments.of(List.of(1, 2, 3, 4, 43, 44), WinningPrice.Four),
                        Arguments.of(List.of(1, 2, 3, 42, 43, 44), WinningPrice.Three)
                );
            }
        }
    }
}
