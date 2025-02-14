package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;
import java.util.stream.Stream;
import lotto.Rank;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RankDeterminerTest {

    @ParameterizedTest
    @MethodSource("winningLottoAndBonusNumber")
    void 입력받은_로또가_몇_등인지_판단할_수_있다(Lotto winningLotto, int bonusNumber, Rank rank) {
        Lotto lotto = new Lotto(Set.of(1, 2, 3, 4, 5, 6));
        assertThat(RankDeterminer.determine(lotto, winningLotto, bonusNumber)).isEqualTo(rank);
    }

    private static Stream<Arguments> winningLottoAndBonusNumber() {
        return Stream.of(
                Arguments.arguments(new Lotto(Set.of(1, 2, 3, 4, 5, 6)), 45, Rank.FIRST),
                Arguments.arguments(new Lotto(Set.of(1, 2, 3, 4, 5, 10)), 6, Rank.SECOND),
                Arguments.arguments(new Lotto(Set.of(1, 2, 3, 4, 5, 10)), 45, Rank.THIRD),
                Arguments.arguments(new Lotto(Set.of(1, 2, 3, 4, 11, 10)), 45, Rank.FOURTH),
                Arguments.arguments(new Lotto(Set.of(1, 2, 3, 12, 11, 10)), 45, Rank.FIFTH)
        );
    }
}
