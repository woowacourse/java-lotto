package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {
    static Stream<Arguments> generateLottos() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new Lotto(ConvertInput.convertLottoNumbers("1, 2, 3, 4, 5, 6")),
                        new Lotto(ConvertInput.convertLottoNumbers("1, 2, 3, 4, 5, 7")),
                        new Lotto(ConvertInput.convertLottoNumbers("1, 2, 3, 4, 5, 9")),
                        new Lotto(ConvertInput.convertLottoNumbers("10, 20, 31, 41, 11, 9"))
                ), new WinningLotto(ConvertInput.convertLottoNumbers("1, 2, 3, 4, 5, 6"), 7))
        );
    }

    @ParameterizedTest
    @DisplayName("등수 계산")
    @MethodSource("generateLottos")
    void checkRankTest(List<Lotto> lottos) {
        LottoResult lottoResult = new LottoResult();
        WinningLotto winningLotto = new WinningLotto(ConvertInput.convertLottoNumbers("1, 2, 3, 4, 5, 6"), 7);

        lottoResult.calculateLottoResult(lottos, winningLotto);

        assertThat(lottoResult.getLottoResult().get(WinningValue.FIRST)).isEqualTo(1);
        assertThat(lottoResult.getLottoResult().get(WinningValue.SECOND)).isEqualTo(1);
        assertThat(lottoResult.getLottoResult().get(WinningValue.THIRD)).isEqualTo(1);
        assertThat(lottoResult.getLottoResult().get(WinningValue.FORTH)).isEqualTo(0);
    }

    @ParameterizedTest
    @DisplayName("수익률 계산")
    @MethodSource("generateLottos")
    void calculateRewardRateTest(List<Lotto> lottos, WinningLotto winningLotto) {
        LottoResult lottoResult = new LottoResult();
        lottoResult.calculateLottoResult(lottos, winningLotto);

        assertThat(lottoResult.calculateRewardRate(25000, lottoResult.getLottoResult()))
                .isEqualTo(8126000);
    }
}
