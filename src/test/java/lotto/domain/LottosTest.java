package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    @Test
    void matchTest() {
        Lottos lottos = new Lottos(Arrays.asList(
                new Lotto(Arrays.asList(1,2,3,4,5,6)),
                new Lotto(Arrays.asList(1,2,3,4,5,7))
        ));
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1,2,3,4,5,6), 7);

        List<Rank> ranks = lottos.match(winningLotto);

        assertThat(ranks).isEqualTo(Arrays.asList(Rank.FIRST, Rank.SECOND));
    }
}
