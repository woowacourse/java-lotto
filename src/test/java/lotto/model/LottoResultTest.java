package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoResultTest {

    @ParameterizedTest
    @CsvSource(value = {"6:SECOND", "44:THIRD"}, delimiter = ':')
    @DisplayName("2, 3등 당첨 여부를 확인한다")
    void matchNumber(int bonusNumberInt, Rank winningRank) {
        LottoFactory testAutoFactory = new ManualLottoFactory(List.of(
            List.of(1, 2, 3, 4, 5, 6)));

        Lottos autoLottos = new Lottos(testAutoFactory, 1);
        Lottos manualLottos = new Lottos(new ManualLottoFactory(Collections.emptyList()), 0);

        LottoResult lottoResult = new LottoResult(manualLottos, autoLottos,
            List.of(1, 2, 3, 4, 5, 45),
            bonusNumberInt);

        assertThat(lottoResult.getRankCount(winningRank)).isEqualTo(1);
    }
}
