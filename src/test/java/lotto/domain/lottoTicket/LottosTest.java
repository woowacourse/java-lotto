package lotto.domain.lottoTicket;

import lotto.domain.rank.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LottosTest {
    Lottos lottos;

    @BeforeEach
    void setUp() {
        List<Integer> firstLottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> secondLottoNumbers = Arrays.asList(7, 8, 9, 10, 11, 12);
        List<Lotto> manualLottos = Arrays.asList(new ManualLotto(firstLottoNumbers), new ManualLotto(secondLottoNumbers));
        lottos = new Lottos(manualLottos, 3);
    }

    @Test
    void 수동로또_개수에따라_자동로또_생성하는_테스트() {
        System.out.println(lottos.toString());
    }

    @Test
    void 로또_상태_알아내기_테스트() {
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 7), 8);
        Rank rank = lottos.matchLottoRank(winningLotto);
        System.out.println(rank);
    }

    @Test
    void 로또_상태_2등_알아내기_테스트() {
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 7), 6);
        Rank rank = lottos.matchLottoRank(winningLotto);
        System.out.println(rank);
    }
}
