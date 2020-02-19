package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class RewardRateTest {
    static Stream<Arguments> generateLottos() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                        new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7)),
                        new Lotto(Arrays.asList(1, 2, 3, 4, 5, 9)),
                        new Lotto(Arrays.asList(10, 20, 31, 41, 11, 9))
                ), new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 7))
        );
    }

    @ParameterizedTest
    @DisplayName("수익률 계산")
    @MethodSource("generateLottos")
    void calculateRewardRateTest(List<Lotto> lottos, WinningLotto winningLotto) {
        WinningRankResult winningRankResult = new WinningRankResult();
        winningRankResult.checkRank(lottos, winningLotto);

        assertThat(RewardRate.calculateRewardRate(25000, winningRankResult.getWinningValueResult()))
                .isEqualTo(812);
    }
}
