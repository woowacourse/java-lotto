package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PrizeTierTest {

    static Stream<Arguments> provideCountAndBonusAndExpected() {
        return Stream.of(
                Arguments.of(2, true, PrizeTier.NONE),
                Arguments.of(2, false, PrizeTier.NONE),
                Arguments.of(3, true, PrizeTier.FIFTH),
                Arguments.of(3, false, PrizeTier.FIFTH),
                Arguments.of(4, true, PrizeTier.FOURTH),
                Arguments.of(4, false, PrizeTier.FOURTH),
                Arguments.of(5, true, PrizeTier.SECOND),
                Arguments.of(5, false, PrizeTier.THIRD),
                Arguments.of(6, true, PrizeTier.FIRST),
                Arguments.of(6, false, PrizeTier.FIRST)
        );
    }

    @ParameterizedTest
    @MethodSource("provideCountAndBonusAndExpected")
    void getTier(int matchedCount, boolean bonusMatched, PrizeTier expected) {
        PrizeTier actual = PrizeTier.getTier(matchedCount, bonusMatched);
        assertEquals(expected, actual);
    }
}
