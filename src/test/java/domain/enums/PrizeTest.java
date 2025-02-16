package domain.enums;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PrizeTest {
    static Stream<Arguments> provideGetPrizeOfTestArgs() {
        return Stream.of(
                // 맞춰야 하는 볼 개수, 보너스 볼 필요 여부, 등수
                Arguments.arguments(0, false, Prize.MISS),
                Arguments.arguments(3, false, Prize.FIFTH),
                Arguments.arguments(4, false, Prize.FOURTH),
                Arguments.arguments(5, false, Prize.THIRD),
                Arguments.arguments(5, true, Prize.SECOND),
                Arguments.arguments(6, false, Prize.FIRST)
        );
    }

    @DisplayName("getPrizeOf() 함수가 의도대로 동작하는지 테스트한다.")
    @ParameterizedTest
    @MethodSource("provideGetPrizeOfTestArgs")
    void getPrizeOf_테스트(int matchedCount, boolean isBonusMatched, Prize expectedPrize) {
        // when
        Prize actualPrize = Prize.getPrizeOf(matchedCount, isBonusMatched);

        // then
        Assertions.assertThat(actualPrize).isEqualTo(expectedPrize);
    }
}