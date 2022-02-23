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

    @DisplayName("LottoPrize 생성자 테스트")
    @Test
    void LottoPrize_constructor_test() {
        assertThat(LottoPrize.values()).contains(
                LottoPrize.MISS, LottoPrize.FIFTH, LottoPrize.FOURTH,
                LottoPrize.THIRD, LottoPrize.TWICE, LottoPrize.FIRST
        );
    }

    @DisplayName("match 메서드 테스트")
    @ParameterizedTest(name = DISPLAY_NAME_ARGUMENTS)
    @MethodSource("matchTestSet")
    void match_test(int lottoNumberMatches, int bonusNumberMatches, LottoPrize result) {
        LottoPrize lottoPrize = LottoPrize.match(lottoNumberMatches, bonusNumberMatches);
        assertThat(lottoPrize).isEqualTo(result);
    }

    private static Stream<Arguments> matchTestSet() {
        return Stream.of(
                Arguments.of(2, 0, LottoPrize.MISS),
                Arguments.of(3, 0, LottoPrize.FIFTH),
                Arguments.of(4, 0, LottoPrize.FOURTH),
                Arguments.of(5, 0, LottoPrize.THIRD),
                Arguments.of(5, 1, LottoPrize.TWICE),
                Arguments.of(6, 0, LottoPrize.FIRST)
        );
    }
}
