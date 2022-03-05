package lottoTest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import lotto.domain.Rank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
class RankTest {

    @ParameterizedTest(name = "[{index}] 매치 결과 : {0}, 반환 결과 : {2}")
    @MethodSource("provideLottoData")
    void 매치_결과_반환_테스트(int inputTotal, boolean inputBonusNumber, Rank inputRank) {
        assertThat(Rank.getMatchResult(inputTotal, inputBonusNumber)).isEqualTo(inputRank);
    }

    private static Stream<Arguments> provideLottoData() {
        return Stream.of(
                Arguments.of(2, true, Rank.MATCH_MISS),
                Arguments.of(2, false, Rank.MATCH_MISS),
                Arguments.of(3, false, Rank.MATCH_THREE_NUMBERS),
                Arguments.of(3, false, Rank.MATCH_THREE_NUMBERS),
                Arguments.of(4, false, Rank.MATCH_FOUR_NUMBERS),
                Arguments.of(4, false, Rank.MATCH_FOUR_NUMBERS),
                Arguments.of(6, false, Rank.MATCH_SIX_NUMBERS),
                Arguments.of(6, false, Rank.MATCH_SIX_NUMBERS)
        );
    }

    @Test
    void 매치_결과_2등_반환_테스트() {
        assertThat(Rank.getMatchResult(5, true))
                .isEqualTo(Rank.MATCH_FIVE_AND_BONUS_NUMBERS);
    }

    @Test
    void 매치_결과_3등_반환_테스트() {
        assertThat(Rank.getMatchResult(5, false))
                .isEqualTo(Rank.MATCH_FIVE_NUMBERS);
    }
}
