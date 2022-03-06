package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lotto.model.numbergenerator.LottoNumberGenerator;
import lotto.model.numbergenerator.ManualGenerator;

class LottoResultTest {

    @ParameterizedTest
    @CsvSource(value = {"6:SECOND", "44:THIRD"}, delimiter = ':')
    @DisplayName("2, 3등 당첨 여부를 확인한다")
    void matchNumber(int bonusNumberInt, Rank winningRank) {
        Lottos autoLottos = new Lottos(new TestNumberGenerator(), 1);
        Lottos manualLottos = new Lottos(new ManualGenerator(Collections.emptyList()), 0);

        LottoResult lottoResult = new LottoResult(manualLottos, autoLottos,
            Set.of(1, 2, 3, 4, 5, 45),
            bonusNumberInt);

        assertThat(lottoResult.getRankCount(winningRank)).isEqualTo(1);
    }

    static class TestNumberGenerator implements LottoNumberGenerator {

        @Override
        public Set<Integer> generate() {
            return Set.of(1, 2, 3, 4, 5, 6);
        }
    }
}
