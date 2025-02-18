package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LottosTest {
    private static LottosFactory lottosFactory;

    @BeforeAll
    static void setUp() {
        lottosFactory = new LottosFactory();
    }

    @Test
    void 로또_결과를_계산할_수_있다() {
        //given
        Lottos lottos = lottosFactory.from(1, () -> List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = WinningLotto.of(List.of(1, 2, 3, 4, 5, 6), 7);

        //when-then
        assertThat(lottos.calculateWinningResult(winningLotto).get(Rank.FIRST)).isEqualTo(1);
    }
}
