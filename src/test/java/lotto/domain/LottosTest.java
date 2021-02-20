package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lotto.domain.ManualLottoGeneratorTest.createCustomLotto;
import static lotto.domain.WinningLottoTest.createCustomWinningLotto;
import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    @DisplayName("가지고 있는 로또와 당첨번호를 비교해서 결과를 제대로 반환 하는지")
    @Test
    void getResults() {
        Lotto lotto1 = createCustomLotto("1, 2, 3, 19, 20, 40");
        Lotto lotto2 = createCustomLotto("1, 2, 20, 25, 29, 45");
        Lottos lottos = new Lottos(Arrays.asList(lotto1, lotto2));

        WinningLotto winningLotto = createCustomWinningLotto("1, 2, 3, 4, 5, 6", "20");
        List<Rank> ranks = lottos.getResults(winningLotto);

        List<Rank> expectedRanks = Arrays.asList(Rank.FIFTH, Rank.NONE);

        assertThat(ranks).isEqualTo(expectedRanks);
    }
}
