package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningTierTest {

    @DisplayName("매칭 번호 개수와 보너스볼 매칭 여부에 해당하는 당첨 타입을 구할 수 있다.")
    @ParameterizedTest
    @MethodSource
    void 매칭_번호_개수와_보너스볼_매칭_여부에_해당하는_당첨_타입을_구할_수_있다(
            int matchedCount, boolean isBonusNumberMatched, WinningTier expectedTier
    ) {
        WinningTier actualTier = WinningTier.find(matchedCount, isBonusNumberMatched);

        assertThat(actualTier).isEqualTo(expectedTier);
    }

    static Stream<Arguments> 매칭_번호_개수와_보너스볼_매칭_여부에_해당하는_당첨_타입을_구할_수_있다() {
        return Stream.of(
                Arguments.of(7, true, WinningTier.EMPTY),
                Arguments.of(7, false, WinningTier.EMPTY),
                Arguments.of(6, true, WinningTier.FIRST),
                Arguments.of(6, false, WinningTier.FIRST),
                Arguments.of(5, true, WinningTier.SECOND),
                Arguments.of(5, false, WinningTier.THIRD),
                Arguments.of(4, true, WinningTier.FOURTH),
                Arguments.of(4, false, WinningTier.FOURTH),
                Arguments.of(3, true, WinningTier.FIFTH),
                Arguments.of(3, false, WinningTier.FIFTH),
                Arguments.of(2, true, WinningTier.EMPTY),
                Arguments.of(2, false, WinningTier.EMPTY)
        );
    }
}