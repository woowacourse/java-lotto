package lotto.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinStatTest {
    LottoRule rule = new KoreaLottoRule();
    Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6), rule);
    Lotto lotto2 = new Lotto(Arrays.asList(1, 3, 5, 7, 9, 11), rule);
    Lottos lottos = new Lottos(5, 2);
    List<Lotto> lottoList;
    WinningLotto winLotto;
    WinStat stat;

    @BeforeEach
    void setUp() {
        lottoList = new ArrayList<>();
        lottoList.add(lotto1);
        lottoList.add(lotto2);
        winLotto = new WinningLotto(lotto1, 12);
        lottos.add(lottoList);
        stat = new WinStat(lottos, winLotto, new KoreaLottoRule());
    }

    @Test
    void getTotalPrizeMoney() {
        assertThat(stat.getTotalPrizeMoney()).isEqualTo(2000005000);
    }

    @Test
    void getProfitRate() {
        assertThat(stat.getProfitRate()).isEqualTo(1000002.5);
    }
}