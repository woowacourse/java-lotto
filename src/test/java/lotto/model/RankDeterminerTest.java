package lotto.model;

import static lotto.model.LottoNumberFixture.*;
import static org.assertj.core.api.Assertions.*;

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
    void 입력받은_로또가_몇_등인지_판단할_수_있다(Lotto winningLotto, LottoNumber bonusNumber, Rank rank) {
        Lotto lotto = new Lotto(generateLottoNumbersInRange(1, 6));
        assertThat(RankDeterminer.determine(lotto, winningLotto, bonusNumber)).isEqualTo(rank);
    }

    private static Stream<Arguments> winningLottoAndBonusNumber() {
        return Stream.of(
                Arguments.arguments(new Lotto(generateLottoNumbersInRange(1, 6)), new LottoNumber(45), Rank.FIRST),
                Arguments.arguments(new Lotto(generateLottoNumbersInRange(2, 7)), new LottoNumber(1), Rank.SECOND),
                Arguments.arguments(new Lotto(generateLottoNumbersInRange(2, 7)), new LottoNumber(45), Rank.THIRD),
                Arguments.arguments(new Lotto(generateLottoNumbersInRange(3, 8)), new LottoNumber(45), Rank.FOURTH),
                Arguments.arguments(new Lotto(generateLottoNumbersInRange(4, 9)), new LottoNumber(45), Rank.FIFTH)
        );
    }
}
