package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoPrizeTest {

    public static final String DISPLAY_NAME_ARGUMENTS = "{displayName} : {arguments}";

    @DisplayName("LottoPrize Enum의 values()를 확인한다")
    @Test
    void LottoPrize_constructor_test() {
        assertThat(LottoPrize.values()).contains(
                LottoPrize.MISS, LottoPrize.FIFTH, LottoPrize.FOURTH,
                LottoPrize.THIRD, LottoPrize.TWICE, LottoPrize.FIRST
        );

    }

    @DisplayName("당첨 번호와 보너스 번호에 맞는 등수를 반환한다")
    @ParameterizedTest(name = DISPLAY_NAME_ARGUMENTS)
    @MethodSource("matchTestSet")
    void match_test(int lottoNumberMatchCount, boolean bonusNumberMatch, LottoPrize result) {
        LottoPrize lottoPrize = LottoPrize.match(lottoNumberMatchCount, bonusNumberMatch);
        assertThat(lottoPrize).isEqualTo(result);
    }

    private static Stream<Arguments> matchTestSet() {
        return Stream.of(
                Arguments.of(0, false, LottoPrize.MISS),
                Arguments.of(1, false, LottoPrize.MISS),
                Arguments.of(2, false, LottoPrize.MISS),
                Arguments.of(3, false, LottoPrize.FIFTH),
                Arguments.of(3, true, LottoPrize.FIFTH),
                Arguments.of(4, false, LottoPrize.FOURTH),
                Arguments.of(4, true, LottoPrize.FOURTH),
                Arguments.of(5, false, LottoPrize.THIRD),
                Arguments.of(5, true, LottoPrize.TWICE),
                Arguments.of(6, false, LottoPrize.FIRST),
                Arguments.of(6, true, LottoPrize.FIRST)
        );
    }
}
