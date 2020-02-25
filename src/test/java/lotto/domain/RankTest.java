package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RankTest {

    @ParameterizedTest
    @MethodSource("provideWinningNumberAndBonusBall")
    @DisplayName("당첨갯수에 해당하는 순위 산출")
    void valueOfWithValidMatchNumber(int mathNumber, boolean matchBonus, Rank expected) {
        assertThat(Rank.valueOf(mathNumber, matchBonus)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideWinningNumberAndBonusBall() {
        return Stream.of(
            Arguments.arguments(6, false, Rank.FIRST),
            Arguments.arguments(6, true, Rank.FIRST),
            Arguments.arguments(5, true, Rank.SECOND),
            Arguments.arguments(5, false, Rank.THIRD),
            Arguments.arguments(4, true, Rank.FOURTH),
            Arguments.arguments(4, false, Rank.FOURTH),
            Arguments.arguments(3, true, Rank.FIFTH),
            Arguments.arguments(3, false, Rank.FIFTH)
        );
    }

    @Test
    @DisplayName("당첨된 갯수에 해당하는 순위가 없음")
    void valueOfWithInvalidMatchNumber() {
        //given
        // 8는 순위에 해당되지 않는 숫자입니다.
        int invalidWinningNumber = 8;

        //when & then
        assertThatThrownBy(() -> Rank.valueOf(invalidWinningNumber, true)).isInstanceOf(
            IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideRank")
    @DisplayName("순위에 따른 당첨금액 산출")
    void calculateWinningMoney(Rank rank, Money winningMoney) {
        assertThat(rank.calculateWinningMoney()).isEqualTo(winningMoney);
    }

    private static Stream<Arguments> provideRank() {
        return Stream.of(
            Arguments.arguments(Rank.FIRST, new Money(2_000_000_000)),
            Arguments.arguments(Rank.SECOND, new Money(30_000_000)),
            Arguments.arguments(Rank.THIRD, new Money(1_500_000)),
            Arguments.arguments(Rank.FOURTH, new Money(50_000)),
            Arguments.arguments(Rank.FIFTH, new Money(5_000))
        );
    }

    @Test
    @DisplayName("enum의 valueOf 학습테스트 정상")
    void valueOfDefaultEnumWithNormalCase() {
        assertThat(Rank.valueOf("FIRST")).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("enum의 valueOf 학습테스트 비정상")
    void valueOfDefaultEnumWithAbnormalCase() {
        assertThatThrownBy(() -> assertThat(Rank.valueOf("INVALID")))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
