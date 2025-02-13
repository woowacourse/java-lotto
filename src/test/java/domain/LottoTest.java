package domain;

import static global.exception.ExceptionMessage.DUPLICATED_NUMBER;
import static global.exception.ExceptionMessage.INVALID_FORMAT;
import static global.exception.ExceptionMessage.INVALID_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @Nested
    class 로또_번호_테스트 {
        @Test
        void 로또_번호_범위_테스트() {
            assertThatThrownBy(() -> {
                new Lotto("1,2,3,4,5,46");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_RANGE.getMessage());
        }

        @Test
        void 로또_번호_구분자_테스트() {
            assertThatThrownBy(() -> {
                new Lotto("1/2/3/4/5/6");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_FORMAT.getMessage());
        }

        @Test
        void 로또_번호_중복_테스트() {
            assertThatThrownBy(() -> {
                new Lotto("1,2,3,4,5,5");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(DUPLICATED_NUMBER.getMessage());
        }

        @Test
        void 로또_번호_길이_테스트() {
            assertThatThrownBy(() -> {
                new Lotto("1,2,3,4,5");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_FORMAT.getMessage());
        }

        @Test
        void 로또_번호_정수_테스트() {
            assertThatThrownBy(() -> {
                new Lotto("1,2,3,4,5,a");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_FORMAT.getMessage());
        }

    }

    @Nested
    class 보너스_번호_테스트 {

        @Test
        void 보너스_번호_범위_테스트() {
            //given
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            //when-then
            assertThatThrownBy(() -> {
                lotto.validateBonus("46");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_RANGE.getMessage());
        }

        @Test
        void 보너스_번호_중복_테스트() {
            //given
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            //when-then
            assertThatThrownBy(() -> {
                lotto.validateBonus("6");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(DUPLICATED_NUMBER.getMessage());
        }
    }

    @ParameterizedTest
    @MethodSource("testMatchRank")
    void countMatchNumbers(String numbers, Rank rank) {
        //given
        WinningLotto winningLotto = new WinningLotto("1,2,3,4,5,6", "7");
        Lotto lotto = new Lotto(numbers);
        //when-then
        assertThat(lotto.countMatchNumbers(winningLotto)).isEqualTo(rank);
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

}