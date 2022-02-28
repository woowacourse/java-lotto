package lotto.domain;

import java.util.Optional;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RankTest {

    @Nested
    @DisplayName("멤버 변수로 객체를 찾는 기능은")
    class Of {

        @Nested
        @TestInstance(Lifecycle.PER_CLASS)
        @DisplayName("개수와 boolean값이 주어지면")
        class Context_with_count_and_contain_bonus {

            @ParameterizedTest
            @MethodSource("provideSource")
            @DisplayName("Optional 객체를 반환한다.")
            void it_create_ok(int count, boolean containBonus, Rank expected) {
                Optional<Rank> winningPrice = Rank.of(count, containBonus);

                Assertions.assertThat(winningPrice.get()).isEqualTo(expected);
            }

            Stream<Arguments> provideSource() {
                return Stream.of(
                        Arguments.of(6, false, Rank.First),
                        Arguments.of(5, true, Rank.Second),
                        Arguments.of(5, false, Rank.Third),
                        Arguments.of(4, false, Rank.Fourth),
                        Arguments.of(3, false, Rank.Fifth)
                );
            }
        }
    }
}
