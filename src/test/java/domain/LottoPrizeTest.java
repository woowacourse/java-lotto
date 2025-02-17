package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoPrizeTest {

    @ParameterizedTest
    @MethodSource("provideMatchNumbers")
    void 로또_당첨_등수를_확인한다(int matchCount, boolean isBonusMatch, LottoPrize result) {
        // expected
        assertThat(LottoPrize.match(matchCount, isBonusMatch)).isEqualTo(result);
    }

    public static Stream<Arguments> provideMatchNumbers() {
        return Stream.of(
            Arguments.of(6, false, LottoPrize.FIRST),
            Arguments.of(5, true, LottoPrize.SECOND),
            Arguments.of(5, false, LottoPrize.THIRD),
            Arguments.of(4, false, LottoPrize.FOURTH),
            Arguments.of(3, false, LottoPrize.FIFTH),
            Arguments.of(2, false, LottoPrize.NONE),
            Arguments.of(1, false, LottoPrize.NONE),
            Arguments.of(0, false, LottoPrize.NONE)
        );
    }
}
