package lotto.domain;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WinningLottoTest {

    @ParameterizedTest
    @MethodSource("generateLotto")
    void 로또_번호의_등수를_판정한다(Lotto lotto, Rank rank) {
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        assertThat(winningLotto.calculateWinning(lotto)).isEqualTo(rank);
    }

    @Test
    void 당첨_번호와_보너스_번호가_중복될_경우_예외를_반환한다() {
        assertThatThrownBy(() -> new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    public static Stream<Arguments> generateLotto() {
        return Stream.of(
                Arguments.arguments(new Lotto(List.of(1, 2, 3, 4, 5, 6)), Rank.FIRST),
                Arguments.arguments(new Lotto(List.of(1, 2, 3, 4, 5, 7)), Rank.SECOND),
                Arguments.arguments(new Lotto(List.of(1, 2, 3, 4, 5, 8)), Rank.THIRD),
                Arguments.arguments(new Lotto(List.of(1, 2, 3, 4, 8, 9)), Rank.FOURTH),
                Arguments.arguments(new Lotto(List.of(1, 2, 3, 8, 9, 10)), Rank.FIFTH),
                Arguments.arguments(new Lotto(List.of(1, 2, 8, 9, 10, 11)), Rank.NONE)
        );
    }
}
