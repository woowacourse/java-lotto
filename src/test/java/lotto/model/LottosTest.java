package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottosTest {

    private Lotto lotto1, lotto2;
    private Lottos lottos;

    @BeforeEach
    void init() {
        lotto1 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        lotto2 = new Lotto(Arrays.asList(3, 4, 5, 6, 7, 8));
        lottos = new Lottos(0);
    }

    @Test
    void 저장_테스트() {
        lottos.insert(lotto1);
        assertThat(lottos.getLottos()).contains(lotto1);
    }

    @Test
    void 등수_개수_테스트() {
        lottos.insert(lotto1);
        lottos.insert(lotto2);
        lottos.calculateRanks(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
        lottos.countRank();
        assertThat(lottos.getCount(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    void 수익률_테스트() {
        lottos.insert(lotto1); // 1등
        lottos.insert(lotto2); // 4등
        lottos.calculateRanks(Arrays.asList(1, 2, 3, 4, 5, 6), 7);
        lottos.countRank();
        assertThat(lottos.getRevenue()).isEqualTo((2000000000 + 50000) / 2000);
    }
}
