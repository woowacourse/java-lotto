package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {

    private static Stream<Arguments> getRanks() {
        return Stream.of(Arguments.of(6, false, LottoRank.FIRST_PRIZE),
                Arguments.of(5, true, LottoRank.SECOND_PRIZE),
                Arguments.of(5, false, LottoRank.THIRD_PRIZE),
                Arguments.of(4, false, LottoRank.FOURTH_PRIZE),
                Arguments.of(4, true, LottoRank.FOURTH_PRIZE),
                Arguments.of(3, false, LottoRank.FIFTH_PRIZE),
                Arguments.of(3, true, LottoRank.FIFTH_PRIZE),
                Arguments.of(2, true, LottoRank.MISS),
                Arguments.of(0, false, LottoRank.MISS));
    }

    @DisplayName("당첨 번호에 따라 로또 순위를 반환한다.")
    @ParameterizedTest
    @MethodSource("getRanks")
    void getLottoRank(int matchCounts, boolean isBonusBall, LottoRank rank) {
        LottoRank value = LottoRank.of(matchCounts, isBonusBall);

        assertThat(value).isEqualTo(rank);
    }
}
