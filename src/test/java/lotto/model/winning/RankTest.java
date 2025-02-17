package lotto.model.winning;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    @DisplayName("일치개수와 보너스넘버 일치여부에 따라 등수를 찾아준다.")
    @MethodSource("provideRankCategory")
    @ParameterizedTest
    void findMatchingRank(int matchingCount, boolean hasBonus, Rank expected) {
        assertThat(Rank.findBy(matchingCount, hasBonus)).isEqualTo(expected);
    }

    @DisplayName("해당 랭크가 당첨인지 미당첨인지 알려준다.")
    @CsvSource(value = {
            "NONE,true", "FIRST,false"
    })
    @ParameterizedTest
    void isNoneOrNot(Rank rank, boolean expected) {
        assertThat(rank.isNone()).isEqualTo(expected);
    }

    @DisplayName("해당 랭크가 보너스 번호가 필요한지 알려준다.")
    @CsvSource(value = {
            "SECOND,true", "THIRD,false"
    })
    @ParameterizedTest
    void isNeedBonusNumber(Rank rank, boolean expected) {
        assertThat(rank.isBonusRequired()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideRankCategory() {
        return Stream.of(
                Arguments.of(6, false, Rank.FIRST),
                Arguments.of(5, true, Rank.SECOND),
                Arguments.of(5, false, Rank.THIRD),
                Arguments.of(4, true, Rank.FOURTH),
                Arguments.of(3, true, Rank.FIFTH),
                Arguments.of(2, true, Rank.NONE)
        );
    }

}
