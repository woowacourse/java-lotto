package domain;

import static domain.exception.ExceptionMessage.DUPLICATED_NUMBER;
import static domain.exception.ExceptionMessage.INVALID_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinningLottoTest {
    @ParameterizedTest
    @MethodSource("testMatchRank")
    void 로또_결과에_따른_랭크를_반환한다(List<Integer> numbers, Rank rank) {
        //given
        WinningLotto winningLotto = WinningLotto.of(List.of(1,2,3,4,5,6), 7);
        Lotto lotto = Lotto.from(numbers);

        //when-then
        assertThat(winningLotto.countMatchNumbers(lotto)).isEqualTo(rank);
    }

    private static Stream<Arguments> testMatchRank() {
        return Stream.of(
                Arguments.of(List.of(1,2,3,4,5,6), Rank.FIRST),
                Arguments.of(List.of(1,2,3,4,5,7), Rank.SECOND),
                Arguments.of(List.of(1,2,3,4,5,8), Rank.THIRD),
                Arguments.of(List.of(1,2,3,4,8,9), Rank.FOURTH),
                Arguments.of(List.of(1,2,3,8,9,10), Rank.FIFTH),
                Arguments.of(List.of(1,2,8,9,10,11), Rank.NONE)
        );
    }

    @Nested
    class 보너스_번호_테스트 {

        @Test
        void 보너스_번호의_범위가_다르면_예외가_발생한다() {
            assertThatThrownBy(() -> {
                WinningLotto.of(List.of(1,2,3,4,5,6), 46);
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_RANGE.getMessage());
        }

        @Test
        void 보너스_번호가_중복되면_예외가_발생한다() {
            assertThatThrownBy(() -> {
                WinningLotto.of(List.of(1,2,3,4,5,6), 6);
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(DUPLICATED_NUMBER.getMessage());
        }
    }
}
