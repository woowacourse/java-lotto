package domain;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoPrizeTest {

    public static Stream<Arguments> getLottoPrizeTestCases() {
        return Stream.of(
                Arguments.arguments(2, false, LottoPrize.NOTHING),
                Arguments.arguments(2, true, LottoPrize.NOTHING),
                Arguments.arguments(3, false, LottoPrize.FIFTH),
                Arguments.arguments(3, true, LottoPrize.FIFTH),
                Arguments.arguments(4, false, LottoPrize.FOURTH),
                Arguments.arguments(4, true, LottoPrize.FOURTH),
                Arguments.arguments(5, false, LottoPrize.THIRD),
                Arguments.arguments(5, true, LottoPrize.SECOND),
                Arguments.arguments(6, false, LottoPrize.FIRST)
        );
    }

    @DisplayName("당첨번호 매칭 개수와 보너스 번호 매칭 여부에 따라 알맞는 당첨상을 반환하는지 테스트")
    @ParameterizedTest
    @MethodSource("getLottoPrizeTestCases")
    void getLottoPrize(int countMatched, boolean isBonusNumberMatched, LottoPrize expected) {
        Assertions.assertThat(LottoPrize.getLottoPrize(countMatched, isBonusNumberMatched)).isEqualTo(expected);
    }
}