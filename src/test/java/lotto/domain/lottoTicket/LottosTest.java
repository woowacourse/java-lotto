package lotto.domain.lottoTicket;

import lotto.domain.rank.RankResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    Lottos lottos;

    @BeforeEach
    void setUp() {
        List<Integer> firstLottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> secondLottoNumbers = Arrays.asList(7, 8, 9, 10, 11, 12);
        List<Lotto> manualLottos = Arrays.asList(new Lotto(firstLottoNumbers), new Lotto(secondLottoNumbers));
        lottos = new Lottos(manualLottos, 0);
    }

    @Test
    void 수동로또_개수에따라_자동로또_생성하는_테스트() {
        List<Lotto> autoLotto = Lottos.createAutoLottos(3);
        assertThat(autoLotto.size()).isEqualTo(3);
    }

    @Test
    void 로또_상태_알아내기_테스트() {
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 7), 8);
        RankResult rank = lottos.matchLottoRank(winningLotto);

        List<Lotto> testManualLotto = Arrays.asList(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Lottos testLottos = new Lottos(testManualLotto, 0);
        assertThat(rank.toString()).isEqualTo(testLottos.matchLottoRank(winningLotto).toString());
    }

    @Test
    void 로또_상태_2등_알아내기_테스트() {
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 7), 6);
        RankResult rank = lottos.matchLottoRank(winningLotto);

        List<Lotto> testManualLotto = Arrays.asList(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Lottos testLottos = new Lottos(testManualLotto, 0);
        assertThat(rank.toString()).isEqualTo(testLottos.matchLottoRank(winningLotto).toString());
    }
}
