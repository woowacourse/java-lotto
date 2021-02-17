package lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinningLottoTest {
    @Test
    void init() {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        // when
        ThrowingCallable throwingCallable
            = () -> WinningLotto.of(numbers, bonusNumber);

        // then
        assertThatCode(throwingCallable).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("generateLottos")
    void match_ReturnProperRank(Rank rank, Lotto lotto) {
        // given
        List<Integer> winningLottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningLotto winningLotto = WinningLotto.of(winningLottoNumbers, bonusNumber);

        // when
        Rank matchRank = winningLotto.match(lotto);

        // then
        assertThat(matchRank).isEqualTo(rank);
    }

    private static Stream<Arguments> generateLottos() {
        return Stream.of(
            Arguments.of(Rank.FIRST, Lotto.fromNumbers(Arrays.asList(1, 2, 3, 4, 5, 6))),
            Arguments.of(Rank.SECOND, Lotto.fromNumbers(Arrays.asList(1, 2, 3, 4, 5, 7))),
            Arguments.of(Rank.THIRD, Lotto.fromNumbers(Arrays.asList(1, 2, 3, 4, 5, 45))),
            Arguments.of(Rank.FOURTH, Lotto.fromNumbers(Arrays.asList(1, 2, 3, 4, 44, 45))),
            Arguments.of(Rank.FIFTH, Lotto.fromNumbers(Arrays.asList(1, 2, 3, 43, 44, 45))),
            Arguments.of(Rank.NOTHING, Lotto.fromNumbers(Arrays.asList(1, 2, 42, 43, 44, 45))),
            Arguments.of(Rank.NOTHING, Lotto.fromNumbers(Arrays.asList(1, 41, 42, 43, 44, 45))),
            Arguments.of(Rank.NOTHING, Lotto.fromNumbers(Arrays.asList(40, 41, 42, 43, 44, 45)))
        );
    }
}
