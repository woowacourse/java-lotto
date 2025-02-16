package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningConditionTest {

    static final int INVALID_MATCHES = 3;
    static final int VALID_MARCHES = 5;

    @DisplayName("보너스볼 매칭이 필요한 경우 당첨 조건 만족여부를 확인할 수 있다.")
    @ParameterizedTest
    @MethodSource()
    void 보너스볼_매칭이_필요한_경우_당첨_조건_만족여부를_확인할_수_있다(
            int givenMatches, boolean isBonusMatched, boolean expectedResult
    ) {
        WinningCondition givenCondition = new WinningCondition(VALID_MARCHES, true);

        boolean actualMatched = givenCondition.isWinningCondition(givenMatches, isBonusMatched);

        assertThat(actualMatched).isEqualTo(expectedResult);
    }

    static Stream<Arguments> 보너스볼_매칭이_필요한_경우_당첨_조건_만족여부를_확인할_수_있다() {
        return Stream.of(
                Arguments.of(INVALID_MATCHES, false, false),
                Arguments.of(INVALID_MATCHES, true, false),
                Arguments.of(VALID_MARCHES, false, false),
                Arguments.of(VALID_MARCHES, true, true));
    }

    @DisplayName("보너스볼 매칭이 불필요한 경우 당첨 조건 만족여부를 확인할 수 있다.")
    @ParameterizedTest
    @MethodSource()
    void 보너스볼_매칭이_불필요한_경우_당첨_조건_만족여부를_확인할_수_있다(
            int givenMatches, boolean isBonusMatched, boolean expectedResult
    ) {
        WinningCondition givenCondition = new WinningCondition(VALID_MARCHES, false);

        boolean actualMatched = givenCondition.isWinningCondition(givenMatches, isBonusMatched);

        assertThat(actualMatched).isEqualTo(expectedResult);
    }

    static Stream<Arguments> 보너스볼_매칭이_불필요한_경우_당첨_조건_만족여부를_확인할_수_있다() {
        return Stream.of(
                Arguments.of(INVALID_MATCHES, false, false),
                Arguments.of(INVALID_MATCHES, true, false),
                Arguments.of(VALID_MARCHES, false, true),
                Arguments.of(VALID_MARCHES, true, true));
    }
}