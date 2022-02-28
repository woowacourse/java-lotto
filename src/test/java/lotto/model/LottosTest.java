package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    private Lotto lotto1;
    private Lotto lotto2;
    private Lotto lotto3;
    private List<Lotto> lottoList;

    @BeforeEach
    void init() {
        lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        lotto3 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        lottoList = new ArrayList<>(List.of(lotto1, lotto2, lotto3));
    }

    @Test
    @DisplayName("Lottos에 Lotto 저장 테스트")
    void lottosSaveTest() {
        Lottos lottos = new Lottos(lottoList);

        assertThat(lottos.getLottos()).containsExactly(lotto1, lotto2, lotto3);
        assertThat(lottos.findLottoCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("로또의 등수 계산하는 테스트")
    void countLottoRankTest() {
        Lottos lottos = new Lottos(lottoList);
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        LottoStatistics statistics = lottos.checkRank(winningLotto);

        assertThat(statistics.getRankMap().get(Rank.FIRST)).isEqualTo(1);
        assertThat(statistics.getRankMap().get(Rank.SECOND)).isEqualTo(2);
    }
}