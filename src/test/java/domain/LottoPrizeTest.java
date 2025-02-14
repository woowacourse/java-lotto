package domain;

import dto.LottoMatchResult;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoPrizeTest {
    
    @ParameterizedTest
    @MethodSource("provideMatchNumbers")
    void 로또_당첨_등수를_확인한다(int matchCount, boolean isBonusMatch, Optional<LottoPrize> result) {
        // expected
        assertThat(LottoPrize.match(new LottoMatchResult(matchCount, isBonusMatch))).isEqualTo(result);
    }
    
    public static Stream<Arguments> provideMatchNumbers() {
        return Stream.of(
                Arguments.of(6, true, Optional.of(LottoPrize.FIRST)),
                Arguments.of(6, false, Optional.of(LottoPrize.FIRST)),
                Arguments.of(5, true, Optional.of(LottoPrize.SECOND)),
                Arguments.of(5, false, Optional.of(LottoPrize.THIRD)),
                Arguments.of(4, true, Optional.of(LottoPrize.FOURTH)),
                Arguments.of(4, false, Optional.of(LottoPrize.FOURTH)),
                Arguments.of(3, true, Optional.of(LottoPrize.FIFTH)),
                Arguments.of(3, false, Optional.of(LottoPrize.FIFTH)),
                Arguments.of(2, true, Optional.empty()),
                Arguments.of(2, false, Optional.empty()),
                Arguments.of(1, true, Optional.empty()),
                Arguments.of(1, false, Optional.empty()),
                Arguments.of(0, true, Optional.empty()),
                Arguments.of(0, false, Optional.empty())
        );
    }
}
