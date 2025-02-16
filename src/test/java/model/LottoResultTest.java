package model;

import dto.LottoResultDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {

    WinningLotto winningLotto;
    UserLotto userLotto;

    @BeforeEach
    void setUp() {
        winningLotto = new WinningLotto(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)), 7);
        userLotto = new UserLotto(new FixedNumberGenerator(), 1000);
    }

    @DisplayName("로또 당첨 결과를 계산한다")
    @Test
    void calculateLottoResult() {
        LottoResultDto lottoResult = new LottoResult(userLotto, winningLotto).toDto();
        Map<Rank, Integer> rankResult = lottoResult.rankResult();
        double profitRate = lottoResult.profitRate();
        Assertions.assertThat(rankResult)
                .containsEntry(Rank.FIRST, 1)
                .containsEntry(Rank.SECOND, 0)
                .containsEntry(Rank.THIRD, 0)
                .containsEntry(Rank.FOURTH, 0)
                .containsEntry(Rank.FIFTH, 0)
                .containsEntry(Rank.FAIL, 0);

        Assertions.assertThat(profitRate)
                .isEqualTo(2000000);

    }

    private static class FixedNumberGenerator implements RandomNumberGenerator {
        @Override
        public Set<Integer> generateNumbers() {
            return Set.of(1, 2, 3, 4, 5, 6);
        }
    }

}