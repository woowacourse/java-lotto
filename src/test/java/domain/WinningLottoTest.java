package domain;

import static global.exception.ExceptionMessage.DUPLICATED_NUMBER;
import static global.exception.ExceptionMessage.INVALID_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinningLottoTest {
    @ParameterizedTest
    @MethodSource("testMatchRank")
    void countMatchNumbers(String numbers, Rank rank) {
        //given
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6", "7");
        Lotto lotto = new Lotto(numbers);

        //when-then
        assertThat(winningLotto.countMatchNumbers(lotto)).isEqualTo(rank);
    }

    private static Stream<Arguments> testMatchRank() {
        return Stream.of(
                Arguments.of("1,2,3,4,5,6", Rank.FIRST),
                Arguments.of("1,2,3,4,5,7", Rank.SECOND),
                Arguments.of("1,2,3,4,5,8", Rank.THIRD),
                Arguments.of("1,2,3,4,8,9", Rank.FOURTH),
                Arguments.of("1,2,3,8,9,10", Rank.FIFTH),
                Arguments.of("1,2,8,9,10,11", Rank.NONE)
        );
    }

    @Nested
    class 보너스_번호_테스트 {

        @Test
        void 보너스_번호_범위_테스트() {
            assertThatThrownBy(() -> {
                new WinningLotto("1,2,3,4,5,6", "46");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_RANGE.getMessage());
        }

        @Test
        void 보너스_번호_중복_테스트() {
            assertThatThrownBy(() -> {
                new WinningLotto("1,2,3,4,5,6", "6");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(DUPLICATED_NUMBER.getMessage());
        }
    }
}
