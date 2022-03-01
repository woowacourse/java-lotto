package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class LottoRankTest {

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    @DisplayName("6개의 번호가 일치하면, 보너스 번호에 상관없이 1등이다")
    void findFirstRank(boolean bonusNumber) {
        final int matchCount = 6;
        final LottoRank expectedRank = LottoRank.FIRST;

        final LottoRank actual = LottoRank.findRank(matchCount, bonusNumber);

        assertThat(expectedRank).isEqualTo(actual);
    }

    @Test
    @DisplayName("5개의 번호가 일치하고, 보너스 번호도 맞으면 2등이다")
    void findSecondRank() {
        final int matchCount = 5;
        final boolean bonusNumber = true;
        final LottoRank expectedRank = LottoRank.SECOND;

        final LottoRank actual = LottoRank.findRank(matchCount, bonusNumber);

        assertThat(expectedRank).isEqualTo(actual);
    }

    @Test
    @DisplayName("5개의 번호가 일치하고, 보너스 번호가 틀리면 3등이다")
    void findThirdRank() {
        final int matchCount = 5;
        final boolean bonusNumber = false;
        final LottoRank expectedRank = LottoRank.THIRD;

        final LottoRank actual = LottoRank.findRank(matchCount, bonusNumber);

        assertThat(expectedRank).isEqualTo(actual);
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    @DisplayName("4개의 번호가 일치하면, 보너스 번호에 상관없이 4등이다")
    void findFourthRank(boolean bonusNumber) {
        final int matchCount = 4;
        final LottoRank expectedRank = LottoRank.FOURTH;

        final LottoRank actual = LottoRank.findRank(matchCount, bonusNumber);

        assertThat(expectedRank).isEqualTo(actual);
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    @DisplayName("3개의 번호가 일치하면, 보너스 번호에 상관없이 5등이다")
    void findFifthRank(boolean bonusNumber) {
        final int matchCount = 3;
        final LottoRank expectedRank = LottoRank.FIFTH;

        final LottoRank actual = LottoRank.findRank(matchCount, bonusNumber);

        assertThat(expectedRank).isEqualTo(actual);
    }

    @ParameterizedTest
    @MethodSource("failMatchCountData")
    @DisplayName("일치하는 번호가 3개보다 적다면, 꽝이다")
    void findFailRank(int matchCount, boolean bonusNumber) {
        final LottoRank expectedRank = LottoRank.FAIL;

        final LottoRank actual = LottoRank.findRank(matchCount, bonusNumber);

        assertThat(expectedRank).isEqualTo(actual);
    }

    static Stream<Arguments> failMatchCountData() {
        return Stream.of(
                Arguments.of(2, true),
                Arguments.of(2, false),
                Arguments.of(1, true),
                Arguments.of(1, false),
                Arguments.of(0, true),
                Arguments.of(0, false)
        );
    }
}
