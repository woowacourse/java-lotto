package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningRankResultTest {
    static Stream<Arguments> generateLottoNumbers() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 6),
                Arguments.of(Arrays.asList(1, 8, 3, 4, 5, 2), 5),
                Arguments.of(Arrays.asList(1, 2, 7, 4, 3, 8), 4));
    }

    @ParameterizedTest
    @DisplayName("로또 일치 개수 계산")
    @MethodSource("generateLottoNumbers")
    void checkCorrectNumberTest(List<Integer> input, int result) {
        List<Integer> winningLottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(WinningRankResult.checkCorrectNumber(input, winningLottoNumbers)).isEqualTo(result);
    }

    @Test
    @DisplayName("보너스볼 일치 여부 확인")
    void isBonusNumberContain() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(WinningRankResult.isBonusNumberContain(lottoNumbers, 6)).isTrue();
        assertThat(WinningRankResult.isBonusNumberContain(lottoNumbers, 7)).isFalse();
    }
}
