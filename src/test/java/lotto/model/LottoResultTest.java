package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import lotto.model.numbergenerator.LottoNumberGenerator;

class LottoResultTest {

    @ParameterizedTest
    @CsvSource(value = {"6:30_000_000", "44:1_500_000"}, delimiter = ':')
    @DisplayName("2, 3등 당첨 여부를 확인한다")
    void matchNumber(int bonusNumberInt, Long totalWinningMoney) {
        Lottos lottos = new Lottos(new TestNumberGenerator(), 1);

        LottoResult lottoResult = new LottoResult(lottos, Arrays.asList(1, 2, 3, 4, 5, 45), bonusNumberInt);

        assertThat(lottoResult.getTotalWinningMoney()).isEqualTo(totalWinningMoney);
    }

    static class TestNumberGenerator implements LottoNumberGenerator {

        @Override
        public List<Integer> generate() {
            return Arrays.asList(1, 2, 3, 4, 5, 6);
        }
    }
}
