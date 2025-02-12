package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.NumbersGenerator;

class WinningResultTest {

    @DisplayName("로또 당첨 결과를 계산을 성공한다.")
    @Test
    void countWinningResultTest() {
        final LottoStore lottoStore = new LottoStore(new FakeRandomNumberGenerator(), new Money(10_000));
        final WinningNumber winningNumber = new WinningNumber(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new BonusNumber(7));
        final WinningResult winningResult = new WinningResult(winningNumber, lottoStore.issueLottos());

        final Map<LottoRank, Integer> lottoRankIntegerMap = winningResult.countWinningResult();

        assertThat(lottoRankIntegerMap.get(LottoRank.RANK_1)).isEqualTo(10);
    }

    static class FakeRandomNumberGenerator implements NumbersGenerator {
        @Override
        public List<Integer> generate() {
            return List.of(1, 2, 3, 4, 5, 6);
        }
    }
}
