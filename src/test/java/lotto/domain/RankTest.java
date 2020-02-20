package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.sun.org.apache.bcel.internal.generic.FALOAD;

class RankTest {

    @ParameterizedTest
    @MethodSource("provideWinningNumberAndBonusBall")
    void 당첨갯수에_해당하는_순위_산출(int mathNumber, boolean matchBonus, Rank expected) {
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
    void 당첨된_갯수에_해당하는_순위가_없음() {
        //given
        int invalidWinningNumber = 2;
        //질문: 제 생각에는 다른 사람이 보기에 invalidWinningNumber가 어떤 의미인지 알기 어려울 것 같아, rank에 없는 값이라는 것을 알려주고 싶어 아래 assertTrue를 넣었습니다. 이게 필요 없는 것일 지 궁금합니다.
        assertTrue(Arrays.stream(Rank.values()).allMatch(rank -> rank.getMatchNumber() != invalidWinningNumber));

        //when & then
        assertThatThrownBy(() -> Rank.valueOf(invalidWinningNumber, true)).isInstanceOf(
            IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideRank")
    void 순위에_따른_당첨금액_산출(Rank rank, int winningMoney) {
        assertThat(rank.calculateWinningMoney()).isEqualTo(winningMoney);
    }

    private static Stream<Arguments> provideRank() {
        return Stream.of(
            Arguments.arguments(Rank.FIRST, 2_000_000_000),
            Arguments.arguments(Rank.SECOND, 30_000_000),
            Arguments.arguments(Rank.THIRD, 1_500_000),
            Arguments.arguments(Rank.FOURTH, 50_000),
            Arguments.arguments(Rank.FIFTH, 5_000)
        );
    }
}
