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
                Arguments.of(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)), 6),
                Arguments.of(new Lotto(Arrays.asList(1, 8, 3, 4, 5, 2)), 5),
                Arguments.of(new Lotto(Arrays.asList(1, 2, 7, 4, 3, 8)), 4));
    }

    static Stream<Arguments> generateLottos() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                        new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7)),
                        new Lotto(Arrays.asList(1, 2, 3, 4, 5, 9)),
                        new Lotto(Arrays.asList(10, 20, 31, 41, 11, 9))
                ))
        );
    }

    @ParameterizedTest
    @DisplayName("로또 일치 개수 계산")
    @MethodSource("generateLottoNumbers")
    void checkCorrectNumberTest(Lotto input, int result) {
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
        assertThat(WinningRankResult.checkCorrectNumber(input, winningLotto)).isEqualTo(result);
    }

    @Test
    @DisplayName("보너스볼 일치 여부 확인")
    void isBonusNumberContainTest() {
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto1 = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 10), 6);
        WinningLotto winningLotto2 = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
        assertThat(WinningRankResult.isBonusNumberContain(lotto, winningLotto1)).isTrue();
        assertThat(WinningRankResult.isBonusNumberContain(lotto, winningLotto2)).isFalse();
    }

    @ParameterizedTest
    @DisplayName("등수 계산")
    @MethodSource("generateLottos")
    void checkRankTest(List<Lotto> lottos) {
        WinningRankResult winningRankResult = new WinningRankResult();
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 7);

        winningRankResult.checkRank(lottos, winningLotto);

        assertThat(winningRankResult.getWinningValueResult().get(WinningValue.FIRST)).isEqualTo(1);
        assertThat(winningRankResult.getWinningValueResult().get(WinningValue.SECOND)).isEqualTo(1);
        assertThat(winningRankResult.getWinningValueResult().get(WinningValue.THIRD)).isEqualTo(1);
        assertThat(winningRankResult.getWinningValueResult().get(WinningValue.FORTH)).isEqualTo(0);
    }
}
