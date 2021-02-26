package domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {

    private static Stream<Arguments> rankWithMatches() {
        return Stream.of(
                Arguments.of(2, true, LottoRank.NONE_PLACE),
                Arguments.of(3, true, LottoRank.FIFTH_PLACE),
                Arguments.of(4, true, LottoRank.FOURTH_MATCHES),
                Arguments.of(6, true, LottoRank.FIRST_PLACE)
        );
    }

    @DisplayName("5개의 숫자가 일치하고, 보너스 번호가 일치하면 2등을 반환한다.")
    @Test
    void lottoRankFindMatches() {
        //given
        int matches = 5;
        boolean hasBonus = true;

        //when
        LottoRank findRank = LottoRank.findRankByBonusAndMatches(hasBonus, matches);

        //then
        assertThat(findRank).isEqualTo(LottoRank.SECOND_PLACE);
    }

    @DisplayName("5개가 일치하지 않으면(2, 3등), 보너스 번호와 상관없이 등수를 반환한다.")
    @ParameterizedTest
    @MethodSource("rankWithMatches")
    void lottoRankFourthMatches(int matches, boolean hasBonus, LottoRank lottoRank) {
        //when
        LottoRank findRank = LottoRank.findRankByBonusAndMatches(hasBonus, matches);

        //then
        assertThat(findRank).isEqualTo(lottoRank);
    }
}