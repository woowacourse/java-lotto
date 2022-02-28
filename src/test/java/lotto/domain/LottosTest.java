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

public class LottosTest {

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
            void It_create_result(Lotto lotto, Rank expected) {
                final Lottos lottos = new Lottos(List.of(lotto));

                final Lotto winningLotto = Lotto.createByManual(List.of(1, 2, 3, 4, 5, 6));
                final Number bonusNumber = Number.getInstance(7);
                final WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

                final Result result = lottos.getResult(winningNumbers);

                assertThat(result.getCount(expected)).isEqualTo(1);
            }

            Stream<Arguments> provideSource() {
                return Stream.of(
                        Arguments.of(Lotto.createByManual(List.of(1, 2, 3, 4, 5, 6)), Rank.First),
                        Arguments.of(Lotto.createByManual(List.of(1, 2, 3, 4, 5, 7)), Rank.Second),
                        Arguments.of(Lotto.createByManual(List.of(1, 2, 3, 4, 5, 45)), Rank.Third),
                        Arguments.of(Lotto.createByManual(List.of(1, 2, 3, 4, 44, 45)), Rank.Fourth),
                        Arguments.of(Lotto.createByManual(List.of(1, 2, 3, 43, 44, 45)), Rank.Fifth)
                );
            }
        }
    }
}
