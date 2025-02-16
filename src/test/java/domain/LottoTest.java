package domain;

import static global.exception.ExceptionMessage.DUPLICATED_NUMBER;
import static global.exception.ExceptionMessage.INVALID_FORMAT;
import static global.exception.ExceptionMessage.INVALID_RANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoTest {

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6", "1,15,17,25,40,45", "1, 2, 3, 4, 5, 6"})
    void 로또_번호_생성_성공_테스트(String inputLotto) {
        assertThatCode(() -> new Lotto(inputLotto)).doesNotThrowAnyException();
    }

    @Nested
    class 로또_번호_실패_테스트 {
        @Test
        void 로또_번호_범위_테스트() {
            assertThatThrownBy(() -> {
                new Lotto("1,2,3,4,5,46");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_RANGE);
        }

        @Test
        void 로또_번호_구분자_테스트() {
            assertThatThrownBy(() -> {
                new Lotto("1/2/3/4/5/6");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_FORMAT);
        }

        @Test
        void 로또_번호_중복_테스트() {
            assertThatThrownBy(() -> {
                new Lotto("1,2,3,4,5,5");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(DUPLICATED_NUMBER);
        }

        @Test
        void 로또_번호_길이_테스트() {
            assertThatThrownBy(() -> {
                new Lotto("1,2,3,4,5");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_FORMAT);
        }

        @Test
        void 로또_번호_정수_테스트() {
            assertThatThrownBy(() -> {
                new Lotto("1,2,3,4,5,a");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_FORMAT);
        }
    }

    @Test
    void 보너스_번호_생성_성공_테스트() {
        //given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        //when-then
        assertThatCode(() -> lotto.validateBonus("45")).doesNotThrowAnyException();
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
                    .hasMessage(INVALID_RANGE);
        }

        @Test
        void 보너스_번호_중복_테스트() {
            //given
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            //when-then
            assertThatThrownBy(() -> {
                lotto.validateBonus("6");
            }).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(DUPLICATED_NUMBER);
        }
    }

    @ParameterizedTest
    @MethodSource("testMatchRank")
    void 당첨번호_순위_매치_정상진행_테스트(String numbers, Rank rank) {
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