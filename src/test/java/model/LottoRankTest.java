package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import model.rank.LottoRank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoRankTest {

    private static Stream<Arguments> provideOverlappedConditionsAndExpectedRanks() {
        return Stream.of(
                Arguments.of(6, false, LottoRank.FIRST),
                Arguments.of(5, true, LottoRank.SECOND),
                Arguments.of(5, false, LottoRank.THIRD),
                Arguments.of(4, false, LottoRank.FOURTH),
                Arguments.of(3, false, LottoRank.FIFTH),
                Arguments.of(2, false, null)
        );
    }

    @ParameterizedTest
    @MethodSource("provideOverlappedConditionsAndExpectedRanks")
    void 로또티켓의_당첨_결과를_계산한다(int overlappedCount, boolean isBonusNumberOverlapped, LottoRank expected) {
        // when
        LottoRank actual = LottoRank.findByMatchCondition(overlappedCount, isBonusNumberOverlapped);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 로또티켓의_결과가_순위_밖이라면_null을_반환한다() {
        // when
        LottoRank rank = LottoRank.findByMatchCondition(2, false);

        // then
        assertThat(rank).isNull();
    }
}
